import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  Container,
  Grid,
  Card,
  CardContent,
  Typography,
  Paper,
  Table,
  TableHead,
  TableRow,
  TableCell,
  TableBody,
  Button,
  Stack,
  Chip,
  Box,
  Avatar
} from "@mui/material";

import { getMyLoans } from "../services/loanApi";
import { AuthContext } from "../auth/AuthContext";

function KpiCard({ title, value, color = "primary.main" }) {
  return (
    <Card sx={{ borderRadius: 3, borderLeft: 4, borderColor: color }}>
      <CardContent>
        <Typography variant="overline" color="text.secondary">
          {title}
        </Typography>
        <Typography variant="h4" sx={{ mt: 0.5, fontWeight: 600 }}>
          {value}
        </Typography>
      </CardContent>
    </Card>
  );
}

const statusInfo = (status) => {
  if (status === 1) return { label: "Accepted", color: "success" };
  if (status === 2) return { label: "Rejected", color: "error" };
  return { label: "Pending", color: "warning" };
};

function Dashboard() {
  const [loans, setLoans] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();
  const { keycloak, logout, hasPermission } = useContext(AuthContext);

  const userName =
    keycloak?.tokenParsed?.name ||
    keycloak?.tokenParsed?.preferred_username ||
    "User";

  useEffect(() => {
    fetchLoans();
  }, []);

  const fetchLoans = async () => {
    try {
      const data = await getMyLoans();
      setLoans(data);
    } catch (err) {
      console.error("Failed to fetch loans", err);
    } finally {
      setLoading(false);
    }
  };

  // ✅ Simple client-side KPI calculation (good enough for now)
  const stats = {
    total: loans.length,
    accepted: loans.filter(l => l.status === 1).length,
    rejected: loans.filter(l => l.status === 2).length,
    pending: loans.filter(l => l.status === 0).length
  };

  const initials = userName
    .split(" ")
    .map((n) => n[0])
    .filter(Boolean)
    .slice(0, 2)
    .join("")
    .toUpperCase();

  return (
    <Container maxWidth="xl" sx={{ py: 4 }}>
      <Box
        display="flex"
        justifyContent="space-between"
        alignItems="center"
        flexWrap="wrap"
        gap={2}
        sx={{ mb: 4 }}
      >
        <Box>
          <Typography variant="h5">Loan Applications Dashboard</Typography>
          <Typography variant="body2" color="text.secondary">
            Track your applications and their status.
          </Typography>
        </Box>

        <Stack direction="row" spacing={2} alignItems="center">
          <Stack direction="row" spacing={1.5} alignItems="center">
            <Avatar sx={{ bgcolor: "primary.main", width: 36, height: 36 }}>
              {initials || "U"}
            </Avatar>
            <Typography variant="body2" color="text.secondary">
              Welcome,{" "}
              <Typography component="span" variant="body2" color="text.primary" fontWeight={600}>
                {userName}
              </Typography>
            </Typography>
          </Stack>
          {hasPermission("CREATE_APPLICATION") && (
            <Button variant="contained" onClick={() => navigate("/create")}>
              Create Application
            </Button>
          )}
          <Button variant="outlined" color="error" onClick={logout}>
            Logout
          </Button>
        </Stack>
      </Box>

      <Grid container spacing={3} sx={{ mb: 4 }}>
        <Grid item xs={12} sm={6} md={3}>
          <KpiCard title="Total Applications" value={stats.total} color="primary.main" />
        </Grid>
        <Grid item xs={12} sm={6} md={3}>
          <KpiCard title="Accepted" value={stats.accepted} color="success.main" />
        </Grid>
        <Grid item xs={12} sm={6} md={3}>
          <KpiCard title="Rejected" value={stats.rejected} color="error.main" />
        </Grid>
        <Grid item xs={12} sm={6} md={3}>
          <KpiCard title="Pending" value={stats.pending} color="warning.main" />
        </Grid>
      </Grid>

      <Paper sx={{ p: 3, borderRadius: 3 }}>
        <Typography variant="h6" sx={{ mb: 2 }}>
          Applications
        </Typography>

        {loading ? (
          <Typography color="text.secondary">Loading applications...</Typography>
        ) : loans.length === 0 ? (
          <Typography color="text.secondary">No applications yet.</Typography>
        ) : (
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>ID</TableCell>
                <TableCell>Loan Type</TableCell>
                <TableCell>Amount</TableCell>
                <TableCell>Term</TableCell>
                <TableCell>Status</TableCell>
                <TableCell align="center">Actions</TableCell>
              </TableRow>
            </TableHead>

            <TableBody>
              {loans.map((loan) => {
                const s = statusInfo(loan.status);
                return (
                  <TableRow key={loan.id} hover>
                    <TableCell>{loan.id}</TableCell>
                    <TableCell>{loan.loanType}</TableCell>
                    <TableCell>{loan.loanAmount}</TableCell>
                    <TableCell>{loan.termInMonths}</TableCell>
                    <TableCell>
                      <Chip label={s.label} color={s.color} size="small" />
                    </TableCell>
                    <TableCell align="center">
                      <Button
                        size="small"
                        variant="outlined"
                        onClick={() => navigate(`/applications/${loan.id}`)}
                      >
                        View
                      </Button>
                    </TableCell>
                  </TableRow>
                );
              })}
            </TableBody>
          </Table>
        )}
      </Paper>
    </Container>
  );
}

export default Dashboard;