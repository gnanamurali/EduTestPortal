// ====================== QUIZ NAVIGATION ======================
let currentIndex = 0;
let questions = [];
let totalQuestions = 0;
let remainingTime = 0;
let timerInterval;

// Show one question and hide others
function showQuestion(index) {
  if (index < 0 || index >= totalQuestions) return;

  // Hide all questions
  questions.forEach(q => (q.style.display = 'none'));

  // Show selected question
  questions[index].style.display = 'block';
  currentIndex = index;

  // Toggle buttons visibility
  document.getElementById('prevBtn').style.display =
    currentIndex === 0 ? 'none' : 'inline-block';
  document.getElementById('nextBtn').style.display =
    currentIndex === totalQuestions - 1 ? 'none' : 'inline-block';
  document.getElementById('submitBtn').style.display =
    currentIndex === totalQuestions - 1 ? 'inline-block' : 'none';
}

// ====================== TIMER + SESSION SYNC ======================
function fetchQuizState() {
  console.log('📡 Fetching quiz state...');
  fetch('FetchQuizStateServlet')
    .then(res => res.text())
    .then(text => {
      console.log('📦 Raw response:', text);

      if (!text || text.trim() === '') {
        console.warn('⚠️ Empty state — using default duration.');
        const duration = parseInt(document.getElementById('duration').value);
        remainingTime = duration * 60;
        startTimer();
        return;
      }

      const data = JSON.parse(text);
      remainingTime =
        data.remainingTime ||
        parseInt(document.getElementById('duration').value) * 60;

      // Restore checked answers if any
      if (data.answers) {
        Object.entries(data.answers).forEach(([qid, ans]) => {
          const radio = document.querySelector(
            `input[name='answer_${qid}'][value='${ans}']`
          );
          if (radio) radio.checked = true;
        });
      }

      startTimer();
    })
    .catch(err => {
      console.error('❌ Fetch failed:', err);
      const duration = parseInt(document.getElementById('duration').value);
      remainingTime = duration * 60;
      startTimer();
    });
}

// ====================== TIMER FUNCTION ======================
function startTimer() {
  const timerDisplay = document.getElementById('timer');
  clearInterval(timerInterval);

  timerInterval = setInterval(() => {
    remainingTime--;
    if (remainingTime < 0) remainingTime = 0;

    const min = Math.floor(remainingTime / 60);
    const sec = remainingTime % 60;
    timerDisplay.textContent = `${min}:${sec < 10 ? '0' : ''}${sec}`;

    if (remainingTime <= 60) timerDisplay.style.color = 'red';
    if (remainingTime % 30 === 0) saveProgress();

    if (remainingTime <= 0) {
      clearInterval(timerInterval);
      console.log('⏰ Time up — auto-submitting quiz');
      const form = document.getElementById('quizForm');
      form.classList.add('force-submit');
      form.submit();
    }
  }, 1000);
}

// ====================== SAVE STATE ======================
function saveProgress() {
  fetch('SaveAnswerServlet', {
    method: 'POST',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    body: `remainingTime=${remainingTime}`,
  });
}

function saveAnswer(qid, option) {
  fetch('SaveAnswerServlet', {
    method: 'POST',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    body: `questionId=${qid}&selectedOption=${option}&remainingTime=${remainingTime}`,
  });
  console.log(`💾 Saved Q${qid} = ${option}`);
}

// ====================== INITIALIZATION ======================
window.addEventListener('load', () => {
  console.log('✅ Window fully loaded — initializing quiz.');

  questions = Array.from(document.querySelectorAll('.question'));
  totalQuestions = questions.length;

  console.log(`📄 Found ${totalQuestions} questions on page.`);

  if (totalQuestions === 0) {
    alert('⚠️ No questions detected — check if quizQuestions list is empty.');
    return;
  }

  // Show first question
  showQuestion(currentIndex);

  // Attach event listeners
  const prevBtn = document.getElementById('prevBtn');
  const nextBtn = document.getElementById('nextBtn');

  if (prevBtn && nextBtn) {
    prevBtn.addEventListener('click', () => showQuestion(currentIndex - 1));
    nextBtn.addEventListener('click', () => showQuestion(currentIndex + 1));
  } else {
    console.error('❌ Navigation buttons not found in DOM.');
  }

  // Start timer + fetch progress
  fetchQuizState();
});
