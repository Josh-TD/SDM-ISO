import { defineConfig } from "vite";
import * as path from 'path';
import react from "@vitejs/plugin-react";

export default defineConfig({
  // Set server to default to local host 3000
  server: {
    host: 'localhost',
    port: 3000,
  },

  // Vite changes behavior of paths, so this makes things back to normal
  resolve: {
    alias: [
        { find: '@', replacement: path.resolve(__dirname, 'src') },
    ],
  },

  plugins: [react()],
})