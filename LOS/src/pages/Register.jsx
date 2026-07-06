import { useState, useEffect } from "react";
import {
  Container, Paper, Grid, TextField, Select, MenuItem, Button,
  Typography, FormControl, InputLabel, Divider, Stack, Alert
} from "@mui/material";

import { registerCustomer } from "../services/customerApi";
import { fetchLookupPublic } from "../services/lookupApi";

function Register() {
  const [genders, setGenders] = useState([]);
  const [religions, setReligions] = useState([]);
  const [occupations, setOccupations] = useState([]);
  const [maritalStatuses, setMaritalStatuses] = useState([]);

  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [submitting, setSubmitting] = useState(false);

  const [form, setForm] = useState({
    username: "",
    password: "",
    salutation: "",
    firstname: "",
    lastname: "",
    email: "",
    phone: "",
    dob: "",
    annualincome: "",
    gender: "",
    religion: "",
    occupation: "",
    maritalStatus: ""
  });

  useEffect(() => {
    const loadLookups = async () => {
      try {
        setGenders(await fetchLookupPublic("GENDER"));
        setReligions(await fetchLookupPublic("RELIGION"));
        setMaritalStatuses(await fetchLookupPublic("MARITAL_STATUS"));
        setOccupations(await fetchLookupPublic("OCCUPATION"));
      } catch (err) {
        console.error("Lookup loading failed", err);
      }
    };
    loadLookups();
  }, []);

  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSuccess("");
    setSubmitting(true);

    try {
      await registerCustomer({
        username: form.username,
        password: form.password,
        profile: {
          salutation: form.salutation,
          firstname: form.firstname,
          lastname: form.lastname,
          email: form.email,
          phone: form.phone,
          dob: form.dob,
          gender: Number(form.gender),
          religion: Number(form.religion),
          occupation: Number(form.occupation),
          marital_status: Number(form.maritalStatus),
          annual_income: Number(form.annualincome)
        }
      });

      setSuccess("Profile created! Redirecting you to login…");
      // Full reload to "/" re-runs Keycloak with login-required → Keycloak login page.
      setTimeout(() => { window.location.href = "/"; }, 1500);
    } catch (err) {
      setError(err.message || "Registration failed");
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <Container maxWidth="md" sx={{ py: 4 }}>
      <Paper sx={{ p: 4, borderRadius: 3 }}>
        <Typography variant="h5" gutterBottom>
          Create Your Profile
        </Typography>
        <Typography variant="body2" color="text.secondary" sx={{ mb: 3 }}>
          Register as an applicant. Your login is created automatically — you can sign in right after.
        </Typography>

        {error && <Alert severity="error" sx={{ mb: 2 }}>{error}</Alert>}
        {success && <Alert severity="success" sx={{ mb: 2 }}>{success}</Alert>}

        <form onSubmit={handleSubmit}>
          <Typography variant="h6" sx={{ mb: 2 }}>Login Details</Typography>
          <Grid container spacing={3}>
            <Grid item xs={12} sm={6}>
              <TextField name="username" label="Username" fullWidth required
                value={form.username} onChange={handleChange} />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField name="password" label="Password" type="password" fullWidth required
                value={form.password} onChange={handleChange} />
            </Grid>
          </Grid>

          <Divider sx={{ my: 4 }} />

          <Typography variant="h6" sx={{ mb: 2 }}>Personal Details</Typography>
          <Grid container spacing={3}>
            <Grid item xs={12} sm={6} md={3}>
              <FormControl fullWidth size="small">
                <InputLabel id="salutation-label">Salutation</InputLabel>
                <Select labelId="salutation-label" name="salutation" value={form.salutation}
                  label="Salutation" onChange={handleChange}>
                  <MenuItem value="Mr">Mr.</MenuItem>
                  <MenuItem value="Mrs">Mrs.</MenuItem>
                  <MenuItem value="Ms">Ms.</MenuItem>
                </Select>
              </FormControl>
            </Grid>
            <Grid item xs={12} sm={6} md={4}>
              <TextField name="firstname" label="First Name" fullWidth required
                value={form.firstname} onChange={handleChange} />
            </Grid>
            <Grid item xs={12} sm={6} md={5}>
              <TextField name="lastname" label="Last Name" fullWidth required
                value={form.lastname} onChange={handleChange} />
            </Grid>

            <Grid item xs={12} sm={6}>
              <TextField name="email" label="Email" fullWidth required
                value={form.email} onChange={handleChange} />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField name="phone" label="Phone" fullWidth required
                value={form.phone} onChange={handleChange} />
            </Grid>

            <Grid item xs={12} sm={6}>
              <TextField name="dob" label="DOB" type="date" fullWidth required
                InputLabelProps={{ shrink: true }} value={form.dob} onChange={handleChange} />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField name="annualincome" label="Annual Income" type="number" fullWidth required
                value={form.annualincome} onChange={handleChange} />
            </Grid>

            <Grid item xs={12} sm={6} md={3}>
              <FormControl fullWidth size="small">
                <InputLabel id="gender-label">Gender</InputLabel>
                <Select labelId="gender-label" name="gender" value={form.gender}
                  label="Gender" onChange={handleChange}>
                  {genders.map(g => (
                    <MenuItem key={g.valueCode} value={g.valueCode}>{g.valueDesc}</MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>
            <Grid item xs={12} sm={6} md={3}>
              <FormControl fullWidth size="small">
                <InputLabel id="religion-label">Religion</InputLabel>
                <Select labelId="religion-label" name="religion" value={form.religion}
                  label="Religion" onChange={handleChange}>
                  {religions.map(r => (
                    <MenuItem key={r.valueCode} value={r.valueCode}>{r.valueDesc}</MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>
            <Grid item xs={12} sm={6} md={3}>
              <FormControl fullWidth size="small">
                <InputLabel id="occupation-label">Occupation</InputLabel>
                <Select labelId="occupation-label" name="occupation" value={form.occupation}
                  label="Occupation" onChange={handleChange}>
                  {occupations.map(o => (
                    <MenuItem key={o.valueCode} value={o.valueCode}>{o.valueDesc}</MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>
            <Grid item xs={12} sm={6} md={3}>
              <FormControl fullWidth size="small">
                <InputLabel id="marital-label">Marital Status</InputLabel>
                <Select labelId="marital-label" name="maritalStatus" value={form.maritalStatus}
                  label="Marital Status" onChange={handleChange}>
                  {maritalStatuses.map(m => (
                    <MenuItem key={m.valueCode} value={m.valueCode}>{m.valueDesc}</MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>
          </Grid>

          <Stack direction="row" spacing={2} justifyContent="flex-end" sx={{ mt: 4 }}>
            <Button variant="outlined" color="inherit" href="/" disabled={submitting}>
              Back to Login
            </Button>
            <Button variant="contained" type="submit" disabled={submitting}>
              {submitting ? "Creating…" : "Create Profile"}
            </Button>
          </Stack>
        </form>
      </Paper>
    </Container>
  );
}

export default Register;
