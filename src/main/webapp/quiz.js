
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


document.addEventListener('DOMContentLoaded', () => {
  showQuestion(currentIndex);
});

