import { useState, useEffect } from "react";
import {
  Container,
  Paper,
  Grid,
  TextField,
  Select,
  MenuItem,
  Button,
  Typography,
  FormControl,
  InputLabel,
  Box,
  Divider,
  Stack,
  IconButton
} from "@mui/material";
import DeleteOutlineIcon from "@mui/icons-material/DeleteOutline";
import AddIcon from "@mui/icons-material/Add";

import { useNavigate } from "react-router-dom";
import { createCustomer } from "../services/customerApi";
import { createLoan } from "../services/loanApi";
import { fetchLookup } from "../services/lookupApi";

function CreateApplication() {
  const navigate = useNavigate();

  const [genders, setGenders] = useState([]);
  const [religions, setReligions] = useState([]);
  const [occupations, setOccupations] = useState([]);
  const [maritalStatuses, setMaritalStatuses] = useState([]);

  const [dependants, setDependants] = useState([]);
  const [educations, setEducations] = useState([]);
  const [identities, setIdentities] = useState([]);

  const [form, setForm] = useState({
    salutation: "",
    firstname: "",
    lastname: "",
    email: "",
    phone: "",
    dob: "",
    annualincome: "",
    gender: "",
    occupation: "",
    maritalStatus: "",
    religion: "",
    loanAmount: "",
    loanType: "",
    termInMonths: "",
    startDate: ""
  });

const handleCancel = () => {
  navigate("/");     // or "/dashboard" if that is your route
};

  useEffect(() => {
    const loadLookups = async () => {
      try {
        setGenders(await fetchLookup("GENDER"));
        setReligions(await fetchLookup("RELIGION"));
        setMaritalStatuses(await fetchLookup("MARITAL_STATUS"));
        setOccupations(await fetchLookup("OCCUPATION"));
      } catch (err) {
        console.error("Lookup loading failed", err);
      }
    };
    
    loadLookups();
  }, []);

  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const addItem = (setter, template) =>
    setter(prev => [...prev, { id: crypto.randomUUID(), ...template }]);

  const updateItem = (setter, index, field, value) =>
    setter(prev => {
      const updated = [...prev];
      updated[index][field] = value;
      return updated;
    });

  const removeItem = (setter, index) =>
    setter(prev => prev.filter((_, i) => i !== index));

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const customerPayload = {
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
        annual_income: Number(form.annualincome),

        dependants: dependants,
        education: educations,
        identity: identities
      };

      const customer = await createCustomer(customerPayload);

      const loanPayload = {
        customerId: customer.id,
        loanAmount: Number(form.loanAmount),
        loanType: form.loanType,
        termInMonths: Number(form.termInMonths),
        startDate: form.startDate || new Date().toISOString().split("T")[0]
      };

      await createLoan(loanPayload);

      navigate("/");
    } catch (err) {
      console.error("Application creation failed", err);
    }
  };

  return (
    <Container maxWidth="lg" sx={{ py: 4 }}>
      <Paper sx={{ p: 4, borderRadius: 3 }}>
        <Typography variant="h5" gutterBottom>
          Create Loan Application
        </Typography>
        <Typography variant="body2" color="text.secondary" sx={{ mb: 3 }}>
          Provide applicant details, dependants, education and loan information.
        </Typography>

        <form onSubmit={handleSubmit}>
          <Typography variant="h6" sx={{ mb: 2 }}>Personal Details</Typography>
          <Grid container spacing={3}>
            <Grid item xs={12} sm={6} md={3}>
              <FormControl fullWidth size="small">
                <InputLabel id="salutation-label">Salutation</InputLabel>
                <Select
                  labelId="salutation-label"
                  name="salutation"
                  value={form.salutation}
                  label="Salutation"
                  onChange={handleChange}
                >
                  <MenuItem value="Mr">Mr.</MenuItem>
                  <MenuItem value="Mrs">Mrs.</MenuItem>
                  <MenuItem value="Ms">Ms.</MenuItem>
                </Select>
              </FormControl>
            </Grid>

            <Grid item xs={12} sm={6} md={4}>
              <TextField
                name="firstname"
                label="First Name"
                fullWidth
                value={form.firstname}
                onChange={handleChange}
              />
            </Grid>

            <Grid item xs={12} sm={6} md={5}>
              <TextField
                name="lastname"
                label="Last Name"
                fullWidth
                value={form.lastname}
                onChange={handleChange}
              />
            </Grid>

            <Grid item xs={12} sm={6}>
              <TextField
                name="email"
                label="Email"
                fullWidth
                value={form.email}
                onChange={handleChange}
              />
            </Grid>

            <Grid item xs={12} sm={6}>
              <TextField
                name="phone"
                label="Phone"
                fullWidth
                value={form.phone}
                onChange={handleChange}
              />
            </Grid>

            <Grid item xs={12} sm={6}>
              <TextField
                name="dob"
                label="DOB"
                type="date"
                fullWidth
                InputLabelProps={{ shrink: true }}
                value={form.dob}
                onChange={handleChange}
              />
            </Grid>

            <Grid item xs={12} sm={6}>
              <TextField
                name="annualincome"
                label="Annual Income"
                type="number"
                fullWidth
                value={form.annualincome}
                onChange={handleChange}
              />
            </Grid>

            <Grid item xs={12} sm={6} md={3}>
              <FormControl fullWidth size="small">
                <InputLabel id="gender-label">Gender</InputLabel>
                <Select
                  labelId="gender-label"
                  name="gender"
                  value={form.gender}
                  label="Gender"
                  onChange={handleChange}
                >
                  {genders.map(g => (
                    <MenuItem key={g.valueCode} value={g.valueCode}>
                      {g.valueDesc}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>

            <Grid item xs={12} sm={6} md={3}>
              <FormControl fullWidth size="small">
                <InputLabel id="religion-label">Religion</InputLabel>
                <Select
                  labelId="religion-label"
                  name="religion"
                  value={form.religion}
                  label="Religion"
                  onChange={handleChange}
                >
                  {religions.map(r => (
                    <MenuItem key={r.valueCode} value={r.valueCode}>
                      {r.valueDesc}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>

            <Grid item xs={12} sm={6} md={3}>
              <FormControl fullWidth size="small">
                <InputLabel id="occupation-label">Occupation</InputLabel>
                <Select
                  labelId="occupation-label"
                  name="occupation"
                  value={form.occupation}
                  label="Occupation"
                  onChange={handleChange}
                >
                  {occupations.map(o => (
                    <MenuItem key={o.valueCode} value={o.valueCode}>
                      {o.valueDesc}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>

            <Grid item xs={12} sm={6} md={3}>
              <FormControl fullWidth size="small">
                <InputLabel id="marital-label">Marital Status</InputLabel>
                <Select
                  labelId="marital-label"
                  name="maritalStatus"
                  value={form.maritalStatus}
                  label="Marital Status"
                  onChange={handleChange}
                >
                  {maritalStatuses.map(m => (
                    <MenuItem key={m.valueCode} value={m.valueCode}>
                      {m.valueDesc}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>
          </Grid>

          <Divider sx={{ my: 4 }} />

          <Box display="flex" alignItems="center" justifyContent="space-between" sx={{ mb: 2 }}>
            <Typography variant="h6">Dependants</Typography>
            <Button
              startIcon={<AddIcon />}
              variant="outlined"
              onClick={() =>
                addItem(setDependants, { name: "", relationship: "", age: "" })
              }
            >
              Add Dependant
            </Button>
          </Box>

          {dependants.length === 0 && (
            <Typography variant="body2" color="text.secondary" sx={{ mb: 1 }}>
              No dependants added.
            </Typography>
          )}

          {dependants.map((dep, i) => (
            <Grid container spacing={2} key={dep.id} sx={{ mb: 2 }} alignItems="center">
              <Grid item xs={12} sm={4}>
                <TextField label="Name" fullWidth
                  value={dep.name}
                  onChange={e => updateItem(setDependants, i, "name", e.target.value)}
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField label="Relationship" fullWidth
                  value={dep.relationship}
                  onChange={e => updateItem(setDependants, i, "relationship", e.target.value)}
                />
              </Grid>
              <Grid item xs={10} sm={3}>
                <TextField label="Age" fullWidth
                  value={dep.age}
                  onChange={e => updateItem(setDependants, i, "age", e.target.value)}
                />
              </Grid>
              <Grid item xs={2} sm={1}>
                <IconButton
                  color="error"
                  onClick={() => removeItem(setDependants, i)}
                  aria-label="Remove dependant"
                >
                  <DeleteOutlineIcon />
                </IconButton>
              </Grid>
            </Grid>
          ))}

          <Divider sx={{ my: 4 }} />

          <Box display="flex" alignItems="center" justifyContent="space-between" sx={{ mb: 2 }}>
            <Typography variant="h6">Education</Typography>
            <Button
              startIcon={<AddIcon />}
              variant="outlined"
              onClick={() =>
                addItem(setEducations, { institution: "", qualification: "", yearofqualification: "" })
              }
            >
              Add Education
            </Button>
          </Box>

          {educations.length === 0 && (
            <Typography variant="body2" color="text.secondary" sx={{ mb: 1 }}>
              No education records added.
            </Typography>
          )}

          {educations.map((edu, i) => (
            <Grid container spacing={2} key={edu.id} sx={{ mb: 2 }} alignItems="center">
              <Grid item xs={12} sm={4}>
                <TextField label="Institution" fullWidth
                  value={edu.institution}
                  onChange={e => updateItem(setEducations, i, "institution", e.target.value)}
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField label="Qualification" fullWidth
                  value={edu.qualification}
                  onChange={e => updateItem(setEducations, i, "qualification", e.target.value)}
                />
              </Grid>
              <Grid item xs={10} sm={3}>
                <TextField label="Year of Qualification" fullWidth
                  value={edu.yearofqualification}
                  onChange={e => updateItem(setEducations, i, "yearofqualification", e.target.value)}
                />
              </Grid>
              <Grid item xs={2} sm={1}>
                <IconButton
                  color="error"
                  onClick={() => removeItem(setEducations, i)}
                  aria-label="Remove education"
                >
                  <DeleteOutlineIcon />
                </IconButton>
              </Grid>
            </Grid>
          ))}

          <Divider sx={{ my: 4 }} />

          <Typography variant="h6" sx={{ mb: 2 }}>Loan Details</Typography>
          <Grid container spacing={3}>
            <Grid item xs={12} sm={6}>
              <TextField
                name="loanType"
                label="Loan Type"
                fullWidth
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
            <Button variant="outlined" color="inherit" onClick={handleCancel}>
              Cancel
            </Button>
            <Button variant="contained" type="submit">
              Submit Application
            </Button>
          </Stack>
        </form>
      </Paper>
    </Container>
  );
}

export default CreateApplication;