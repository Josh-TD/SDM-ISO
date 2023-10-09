/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {
      animation: {
        'logo-spin': 'spin infinite 20s linear'
      },

      colors: {
        'iso-slate': '#3E515B',
        'iso-offwhite': '#F4F2EB',
        'iso-dark-text': '#1F303B',
        'iso-secondary-text': '#9DA6AA',
        'iso-border-light': '#E6E6E6'
      },
    },
  },
  plugins: [],
}

