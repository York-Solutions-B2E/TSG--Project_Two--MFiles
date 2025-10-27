import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

export default defineConfig({
    // NOTE: Modify the Port to 3000
    server: {
        port: 3000,
    },
    plugins: [
        react({
            babel: {
                plugins: [["babel-plugin-react-compiler"]],
            },
        }),
    ],
});
