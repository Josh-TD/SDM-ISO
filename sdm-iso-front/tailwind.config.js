/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,js,ts,tsx}"],
  theme: {
    extend: {
      animation: {
        'logo-spin': 'spin infinite 20s linear'
      }
    },
  },
  plugins: [],
}

