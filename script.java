document.addEventListener('DOMContentLoaded', () => {
  // --- Переменные для музыки ---
  const musicBtn = document.getElementById('musicBtn');
  const musicIcon = document.querySelector('.music-icon');
  const backgroundMusic = document.getElementById('backgroundMusic');
  let isPlaying = false;

  // --- Функции для музыки ---
  function toggleMusic() {
    if (isPlaying) {
      backgroundMusic.pause();
      musicIcon.textContent = '🎵';
      musicBtn.classList.remove('playing');
    } else {
      backgroundMusic.play().catch(error => {
        console.log("Автовоспроизведение заблокировано. Нажмите на кнопку.");
        musicIcon.textContent = '⚠️';
        setTimeout(() => {
          musicIcon.textContent = '🎵';
        }, 1000);
      });
      musicIcon.textContent = '⏸️';
      musicBtn.classList.add('playing');
    }
    isPlaying = !isPlaying;
  }

  // --- Обработчик клика ---
  musicBtn.addEventListener('click', toggleMusic);

  // --- Переключение окон ---
  const windows = document.querySelectorAll('.window');
  const navButtons = document.querySelectorAll('.nav-btn');
  let currentWindowIndex = 0;

  function showWindow(index) {
    windows.forEach((window, i) => {
      if (i === index) {
        window.classList.add('active');
      } else {
        window.classList.remove('active');
      }
    });

    navButtons.forEach((button, i) => {
      button.classList.toggle('active', i === index);
    });

    // Останавливаем музыку при переключении окон (опционально)
    if (isPlaying) {
      toggleMusic();
    }
  }

  function goToWindow(index) {
    currentWindowIndex = index;
    showWindow(currentWindowIndex);
  }

  navButtons.forEach(button => {
    button.addEventListener('click', () => {
      const targetIndex = parseInt(button.dataset.target);
      goToWindow(targetIndex);
    });
  });

  showWindow(currentWindowIndex);
});
