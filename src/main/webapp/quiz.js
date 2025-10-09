// -------------------- QUIZ NAVIGATION --------------------

let currentIndex = 0;
const questions = document.querySelectorAll('.question');
const totalQuestions = questions.length;

function showQuestion(index) {
  if (index < 0 || index >= totalQuestions) return;

  questions[currentIndex].style.display = 'none';
  questions[index].style.display = 'block';
  currentIndex = index;

  document.getElementById('prevBtn').style.display = currentIndex === 0 ? 'none' : 'inline-block';
  document.getElementById('nextBtn').style.display = currentIndex === totalQuestions - 1 ? 'none' : 'inline-block';
  document.getElementById('submitBtn').style.display = currentIndex === totalQuestions - 1 ? 'inline-block' : 'none';
}

// -------------------- TIMER + SESSION SYNC --------------------

let remainingTime = 0;
let timerInterval;

// Prevent accidental early submit
document.querySelector("form").addEventListener("submit", (e) => {
  if (remainingTime > 0 && !e.target.classList.contains("force-submit")) {
    console.log("⏳ Preventing accidental early submit");
    e.preventDefault();
  }
});

// Fetch session state (remainingTime + saved answers)
function fetchQuizState() {
  console.log("📡 Fetching quiz state...");
  fetch('FetchQuizStateServlet')
    .then(res => res.text())
    .then(text => {
      console.log("Raw servlet response:", text);

      if (!text || text.trim() === "") {
        console.log("⚠️ Empty response from server — using fallback.");
        const duration = parseInt(document.getElementById("duration").value);
        remainingTime = duration * 60;
        startTimer();
        return;
      }

      const data = JSON.parse(text);
      remainingTime = data.remainingTime;

      // Fallback if null or invalid
      if (!remainingTime || isNaN(remainingTime) || remainingTime <= 0) {
        const duration = parseInt(document.getElementById("duration").value);
        remainingTime = duration * 60;
        console.log("⏱ Fallback: using duration =", remainingTime, "seconds");
      }

      // Restore checked answers
      if (data.answers) {
        for (let qid in data.answers) {
          const selectedOpt = data.answers[qid];
          const radio = document.querySelector(`input[name='answer_${qid}'][value='${selectedOpt}']`);
          if (radio) radio.checked = true;
        }
      }

      startTimer();
    })
    .catch(err => {
      console.error("❌ Fetch failed:", err);
      const duration = parseInt(document.getElementById("duration").value);
      remainingTime = duration * 60;
      startTimer();
    });
}

// Start the countdown
function startTimer() {
  console.log("⏱ Starting timer with:", remainingTime, "seconds left.");
  const timerDisplay = document.getElementById("timer");

  clearInterval(timerInterval);
  timerInterval = setInterval(() => {
    remainingTime--;
    if (remainingTime < 0) remainingTime = 0;

    const minutes = Math.floor(remainingTime / 60);
    const seconds = remainingTime % 60;
    timerDisplay.textContent = `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;

    // Warn visually when less than 1 minute left
    if (remainingTime <= 60) timerDisplay.style.color = 'red';

    // Save progress every 30 seconds
    if (remainingTime % 30 === 0) saveProgress();

    // Auto-submit when time runs out
    if (remainingTime <= 0) {
      clearInterval(timerInterval);
      document.getElementById("timeTaken").value = document.getElementById("duration").value * 60;
      const form = document.querySelector("form");
      form.classList.add("force-submit");
      form.submit();
    }
  }, 1000);
}

// Save current remaining time to session
function saveProgress() {
  fetch('SaveAnswerServlet', {
    method: 'POST',
    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
    body: `remainingTime=${remainingTime}`
  });
}

// Save single answer immediately
function saveAnswer(qid, option) {
  fetch('SaveAnswerServlet', {
    method: 'POST',
    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
    body: `questionId=${qid}&selectedOption=${option}&remainingTime=${remainingTime}`
  });
  console.log(`💾 Saved Q${qid} = ${option}, time left: ${remainingTime}s`);
}

// -------------------- INITIALIZE --------------------

document.addEventListener('DOMContentLoaded', () => {
  console.log("✅ quiz.js loaded successfully");
  showQuestion(currentIndex);
  fetchQuizState();
});
