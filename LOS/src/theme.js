import { createTheme } from "@mui/material/styles";

const theme = createTheme({
  palette: {
    mode: "light",
    primary: {
      main: "#1e3a8a",
      light: "#3b5bdb",
      dark: "#142a66",
      contrastText: "#ffffff"
    },
    secondary: {
      main: "#0d9488",
      light: "#14b8a6",
      dark: "#0f766e",
      contrastText: "#ffffff"
    },
    success: { main: "#16a34a" },
    error:   { main: "#dc2626" },
    warning: { main: "#f59e0b" },
    background: {
      default: "#f4f6fb",
      paper: "#ffffff"
    },
    text: {
      primary: "#0f172a",
      secondary: "#475569"
    },
    divider: "#e2e8f0"
  },
  shape: {
    borderRadius: 10
  },
  typography: {
    fontFamily: '"Inter", "Segoe UI", system-ui, -apple-system, sans-serif',
    h5: { fontWeight: 600 },
    h6: { fontWeight: 600 },
    button: { textTransform: "none", fontWeight: 600 }
  },
  components: {
    MuiPaper: {
      styleOverrides: {
        root: { boxShadow: "0 1px 3px rgba(15,23,42,0.06), 0 1px 2px rgba(15,23,42,0.04)" }
      }
    },
    MuiButton: {
      defaultProps: { disableElevation: true },
      styleOverrides: {
        root: { borderRadius: 8, paddingInline: 16 }
      }
    },
    MuiTextField: {
      defaultProps: { size: "small", variant: "outlined" }
    },
    MuiSelect: {
      defaultProps: { size: "small" }
    },
    MuiTableCell: {
      styleOverrides: {
        head: { fontWeight: 600, backgroundColor: "#f8fafc", color: "#0f172a" }
      }
    }
  }
});

export default theme;
