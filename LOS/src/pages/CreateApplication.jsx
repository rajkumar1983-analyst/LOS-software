import { useState, useEffect } from "react";
import {
  Container,
  Paper,
  Grid,
  TextField,
  Button,
  Typography,
  Divider,
  Stack,
  Alert
} from "@mui/material";

import { useNavigate } from "react-router-dom";
import { getMyProfile } from "../services/customerApi";
import { createLoan } from "../services/loanApi";

function CreateApplication() {
  const navigate = useNavigate();

  const [profile, setProfile] = useState(null);
  const [loadError, setLoadError] = useState("");
  const [error, setError] = useState("");
  const [submitting, setSubmitting] = useState(false);

  const [form, setForm] = useState({
    loanAmount: "",
    loanType: "",
    termInMonths: "",
    startDate: ""
  });

  useEffect(() => {
    const loadProfile = async () => {
      try {
        const me = await getMyProfile();
        setProfile(me);
      } catch (err) {
        console.error("Failed to load profile", err);
        setLoadError(
          "We couldn't find your applicant profile. Please complete registration first."
        );
      }
    };
    loadProfile();
  }, []);

  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const handleCancel = () => navigate("/");

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSubmitting(true);

    try {
      await createLoan({
        customerId: profile.id,
        loanAmount: Number(form.loanAmount),
        loanType: form.loanType,
        termInMonths: Number(form.termInMonths),
        startDate: form.startDate || new Date().toISOString().split("T")[0]
      });

      navigate("/");
    } catch (err) {
      console.error("Application creation failed", err);
      setError(err.message || "Application creation failed");
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <Container maxWidth="lg" sx={{ py: 4 }}>
      <Paper sx={{ p: 4, borderRadius: 3 }}>
        <Typography variant="h5" gutterBottom>
          Create Loan Application
        </Typography>
        <Typography variant="body2" color="text.secondary" sx={{ mb: 3 }}>
          Your application will be created against your existing profile.
        </Typography>

        {loadError && <Alert severity="warning" sx={{ mb: 2 }}>{loadError}</Alert>}
        {error && <Alert severity="error" sx={{ mb: 2 }}>{error}</Alert>}

        {profile && (
          <>
            <Typography variant="h6" sx={{ mb: 2 }}>Applicant</Typography>
            <Grid container spacing={3}>
              <Grid item xs={12} sm={6} md={4}>
                <TextField label="Name" fullWidth disabled
                  value={`${profile.firstname ?? ""} ${profile.lastname ?? ""}`.trim()} />
              </Grid>
              <Grid item xs={12} sm={6} md={4}>
                <TextField label="Email" fullWidth disabled value={profile.email ?? ""} />
              </Grid>
              <Grid item xs={12} sm={6} md={4}>
                <TextField label="Phone" fullWidth disabled value={profile.phone ?? ""} />
              </Grid>
            </Grid>

            <Divider sx={{ my: 4 }} />
          </>
        )}

        <form onSubmit={handleSubmit}>
          <Typography variant="h6" sx={{ mb: 2 }}>Loan Details</Typography>
          <Grid container spacing={3}>
            <Grid item xs={12} sm={6}>
              <TextField
                name="loanType"
                label="Loan Type"
                fullWidth
                required
                value={form.loanType}
                onChange={handleChange}
              />
            </Grid>

            <Grid item xs={12} sm={6}>
              <TextField
                name="loanAmount"
                label="Loan Amount"
                type="number"
                fullWidth
                required
                value={form.loanAmount}
                onChange={handleChange}
              />
            </Grid>

            <Grid item xs={12} sm={6}>
              <TextField
                name="termInMonths"
                label="Term (Months)"
                type="number"
                fullWidth
                required
                value={form.termInMonths}
                onChange={handleChange}
              />
            </Grid>

            <Grid item xs={12} sm={6}>
              <TextField
                name="startDate"
                label="Start Date"
                type="date"
                fullWidth
                InputLabelProps={{ shrink: true }}
                value={form.startDate}
                onChange={handleChange}
              />
            </Grid>
          </Grid>

          <Stack direction="row" spacing={2} justifyContent="flex-end" sx={{ mt: 4 }}>
            <Button variant="outlined" color="inherit" onClick={handleCancel} disabled={submitting}>
              Cancel
            </Button>
            <Button variant="contained" type="submit" disabled={submitting || !profile}>
              {submitting ? "Submitting…" : "Submit Application"}
            </Button>
          </Stack>
        </form>
      </Paper>
    </Container>
  );
}

export default CreateApplication;
