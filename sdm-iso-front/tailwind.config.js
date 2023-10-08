/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {
      animation: {
        'logo-spin': 'spin infinite 20s linear'
      },

      colors: {
        'iso-slate': '#3E515B'
      },
    },
  },
  plugins: [],
}

