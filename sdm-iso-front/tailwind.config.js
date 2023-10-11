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
        'iso-medium-gray': '#9DA6AA',
        'iso-light-gray': '#717070',
        'iso-border-light': '#E6E6E6',
        "iso-link-blue": '#6DABE3'
      },

      fontFamily: {
        'light': ['Open Sans', 'light'],
        'regular': ['Open Sans', 'regular'],
        'semibold': ['Open Sans', 'semibold']
      },
    },
  },
  plugins: [],
}

