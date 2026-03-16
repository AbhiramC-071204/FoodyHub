/**
 * 
 */

document.addEventListener("DOMContentLoaded", () => {
  const toggleBtn = document.getElementById("themeToggle");
  const root = document.documentElement;

  // Load saved theme (default: dark)
  const savedTheme = localStorage.getItem("theme") || "dark";
  root.setAttribute("data-theme", savedTheme);

  // If toggle button doesn't exist on this page → exit safely
  if (!toggleBtn) return;

  // Set correct icon on load
  toggleBtn.innerHTML =
    savedTheme === "dark"
      ? `<ion-icon name="sunny-outline"></ion-icon>`
      : `<ion-icon name="moon-outline"></ion-icon>`;

  // Toggle theme on click
  toggleBtn.addEventListener("click", () => {
    const current = root.getAttribute("data-theme");
    const next = current === "dark" ? "light" : "dark";

    root.setAttribute("data-theme", next);
    localStorage.setItem("theme", next);

    toggleBtn.innerHTML =
      next === "dark"
        ? `<ion-icon name="sunny-outline"></ion-icon>`
        : `<ion-icon name="moon-outline"></ion-icon>`;
  });
});

