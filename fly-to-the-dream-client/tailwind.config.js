/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./app/**/*.{js,ts,jsx,tsx,mdx}",
    "./pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./components/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    extend: {
      colors: {
        'primary-color': '#04aa6d',
        'secondary-color': '#059862',
        'background': '#F5F5F5',
        'button-color': '#facc15',
      },
      fontSize: {
        'main-size': '18px'
      },
      fontFamily: {
        'lexend-deca-light': 'Lexend Deca Light',
        'lexend-deca-medium': 'Lexend Deca Medium',
        'jambono-black': 'Jambono Black',
        'jambono-medium': 'Jambono Medium',
        'jambono-light': 'Jambono Light'
     },
    },
  },
  plugins: [],
}

