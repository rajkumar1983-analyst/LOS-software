import { useContext, useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import {
  Container, Box, Paper, Grid, TextField,
  Typography, Chip, Button, Divider, Stack, Alert
} from "@mui/material";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import { getLoanById, underwriteLoan } from "../services/loanApi";
import { getCustomerById } from "../services/customerApi";
import { AuthContext } from "../auth/AuthContext";

function ViewApplication() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [loan, setLoan] = useState(null);
  const [customer, setCustomer] = useState(null);

  const { hasPermission } = useContext(AuthContext);
  const [comments, setComments] = useState("");
  const [submitting, setSubmitting] = useState(false);
  const [actionError, setActionError] = useState("");

  useEffect(() => {
    loadApplication();
  }, []);

  const submitDecision = async (decision) => {
    setSubmitting(true);
    setActionError("");
    try {
      await underwriteLoan({ id: loan.id, decision, comments });
      await loadApplication(); // refresh to reflect the new status
      setComments("");
    } catch (err) {
      setActionError(err.message || "Failed to submit decision");
    } finally {
      setSubmitting(false);
    }
  };

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

        {hasPermission("UNDERWRITE") && (
          <>
            <Divider sx={{ my: 4 }} />
            <Typography variant="h6" sx={{ mb: 2 }}>Underwriting Decision</Typography>

            {actionError && <Alert severity="error" sx={{ mb: 2 }}>{actionError}</Alert>}

            {loan.status === 1 || loan.status === 2 ? (
              <Alert severity={loan.status === 1 ? "success" : "error"}>
                This application has been {loan.status === 1 ? "accepted" : "declined"}.
                {loan.underwriterComments ? ` Comments: ${loan.underwriterComments}` : ""}
              </Alert>
            ) : (
              <>
                <TextField
                  label="Comments (optional)"
                  value={comments}
                  onChange={(e) => setComments(e.target.value)}
                  fullWidth
                  multiline
                  minRows={2}
                  sx={{ mb: 2 }}
                />
                <Stack direction="row" spacing={2}>
                  <Button
                    variant="contained"
                    color="success"
                    disabled={submitting}
                    onClick={() => submitDecision(1)}
                  >
                    {submitting ? "Submitting…" : "Accept"}
                  </Button>
                  <Button
                    variant="contained"
                    color="error"
                    disabled={submitting}
                    onClick={() => submitDecision(2)}
                  >
                    {submitting ? "Submitting…" : "Decline"}
                  </Button>
                </Stack>
              </>
            )}
          </>
        )}
      </Paper>
    </Container>
  );
}

export default ViewApplication;