/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,jsx,ts,tsx}",],
  theme: {
    extend: {
      backgroundImage: {
        mancala: "url(/ai_generated_mancala_game.png)",
        mancala2: "url(/ai_img_2.png)"
      },
      colors: {
        sogyo: "#007936"
      }
    },
  },
  plugins: [
    require('@tailwindcss/forms'),
    require('@tailwindcss/typography')
  ],
}
