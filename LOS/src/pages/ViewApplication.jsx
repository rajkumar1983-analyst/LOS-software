import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import {
  Container, Box, Paper, Grid, TextField,
  Typography, Chip, Button, Divider
} from "@mui/material";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import { getLoanById } from "../services/loanApi";
import { getCustomerById } from "../services/customerApi";

function ViewApplication() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [loan, setLoan] = useState(null);
  const [customer, setCustomer] = useState(null);

  useEffect(() => {
    loadApplication();
  }, []);

  const loadApplication = async () => {
    try {
      const loanData = await getLoanById(id);
      setLoan(loanData);

      const customerData = await getCustomerById(loanData.customerId);
      setCustomer(customerData);
    } catch (err) {
      console.error("Failed to load application", err);
    }
  };

  const statusColor = (status) => {
    switch (status) {
      case 1: return "success";
      case 2: return "error";
      default: return "warning";
    }
  };

  if (!loan || !customer) {
    return (
      <Container maxWidth="lg" sx={{ py: 4 }}>
        <Typography color="text.secondary">Loading...</Typography>
      </Container>
    );
  }

  return (
    <Container maxWidth="lg" sx={{ py: 4 }}>
      <Button
        variant="outlined"
        startIcon={<ArrowBackIcon />}
        onClick={() => navigate(-1)}
        sx={{ mb: 3 }}
      >
        Back
      </Button>

      <Paper sx={{ p: 4, borderRadius: 3 }}>
        <Box display="flex" justifyContent="space-between" alignItems="center" flexWrap="wrap" gap={2}>
          <Box>
            <Typography variant="h5">Application #{loan.id}</Typography>
            <Typography variant="body2" color="text.secondary">
              Loan and customer details for this application.
            </Typography>
          </Box>

          <Chip
            label={
              loan.status === 1 ? "Accepted" :
              loan.status === 2 ? "Rejected" :
              "Pending"
            }
            color={statusColor(loan.status)}
          />
        </Box>

        <Divider sx={{ my: 3 }} />

        <Typography variant="h6" sx={{ mb: 2 }}>Loan Details</Typography>
        <Grid container spacing={3}>
          <Grid item xs={12} sm={6} md={3}>
            <TextField label="Loan Type" value={loan.loanType} fullWidth disabled />
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <TextField label="Amount" value={loan.loanAmount} fullWidth disabled />
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <TextField label="Term (Months)" value={loan.termInMonths} fullWidth disabled />
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <TextField label="Start Date" value={loan.startDate} fullWidth disabled />
          </Grid>
        </Grid>

        <Divider sx={{ my: 4 }} />

        <Typography variant="h6" sx={{ mb: 2 }}>Customer Details</Typography>
        <Grid container spacing={3}>
          <Grid item xs={12} sm={6} md={4}>
            <TextField label="First Name" value={customer.firstname} fullWidth disabled />
          </Grid>
          <Grid item xs={12} sm={6} md={4}>
            <TextField label="Last Name" value={customer.lastname} fullWidth disabled />
          </Grid>
          <Grid item xs={12} sm={6} md={4}>
            <TextField label="DOB" value={customer.dob} fullWidth disabled />
          </Grid>
          <Grid item xs={12} sm={6} md={6}>
            <TextField label="Email" value={customer.email} fullWidth disabled />
          </Grid>
          <Grid item xs={12} sm={6} md={6}>
            <TextField label="Phone" value={customer.phone} fullWidth disabled />
          </Grid>
        </Grid>
      </Paper>
    </Container>
  );
}

export default ViewApplication;