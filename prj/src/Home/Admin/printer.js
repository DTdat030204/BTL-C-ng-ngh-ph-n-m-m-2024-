import React, { useState } from 'react';
import {
    AppBar,
    Toolbar,
    Button,
    Typography,
    Box,
    Grid,
    Card,
    CardContent,
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    LinearProgress,
    TextField
} from '@mui/material';
import { useNavigate } from 'react-router-dom';
import NotificationsIcon from '@mui/icons-material/Notifications'; // Notification icon
import SettingsIcon from '@mui/icons-material/Settings'; // Settings icon
import AccountCircleIcon from '@mui/icons-material/AccountCircle'; // Account icon
import { styled } from '@mui/material/styles';

const Sidebar = styled('div')({
    width: '150px',
    backgroundColor: '#66B2FF',
    height: 'calc(100vh - 20px)', // Adjust height to account for the margin
    position: 'fixed',
    padding: '20px',
    //marginTop: '20px', // Moves the sidebar down by 20px
});

const MainContent = styled('div')({
    marginLeft: '170px', // Adjusts for the sidebar width
    paddingLeft: '20px', // Adds padding around the main content
    flexGrow: 1,
});

const placeholderImage = '/printer.png'; // Path to the same image for all printers

const printers = [
    { id: 1, name: 'H6 - 101', status: 'Active', prints: 100, date: '2024-11-01', paper: 75, ink: 60 },
    { id: 2, name: 'H6 - 102', status: 'Inactive', prints: 50, date: '2024-11-02', paper: 30, ink: 20 },
    { id: 3, name: 'H6 - 103', status: 'Active', prints: 75, date: '2024-11-03', paper: 90, ink: 80 },
    { id: 4, name: 'H6 - 104', status: 'Active', prints: 100, date: '2024-11-01', paper: 75, ink: 60 },
    { id: 5, name: 'H6 - 105', status: 'Inactive', prints: 50, date: '2024-11-02', paper: 30, ink: 20 },
    { id: 6, name: 'H6 - 106', status: 'Active', prints: 75, date: '2024-11-03', paper: 90, ink: 80 },
    // Add more printers as needed
];

function Home() {
    const navigate = useNavigate();
    const [open, setOpen] = useState(false);
    const [selectedPrinter, setSelectedPrinter] = useState(null);

    const handleClickOpen = (printer) => {
        setSelectedPrinter(printer);
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
        setSelectedPrinter(null);
    };

    return (
        <div style={{ display: 'flex', height: '100vh' }}>
            <Sidebar>
                <Button fullWidth onClick={() => navigate('/')} sx={{ display: 'flex', justifyContent: 'flex-start' }}>
                    
                    <img src="/Avatar.png" alt="Icon" width="60" height="30" />
                    <Typography variant='h5' style={{ fontWeight: 'bold' }} align='left' sx={{ flexGrow: 1 }} color='#FFFFFF'> SSPS </Typography>
                </Button>
                <Button fullWidth onClick={() => navigate('/home/admin')} sx={{ textAlign: 'left', justifyContent: 'flex-start', color: '#ffffff' }}>Trang chủ</Button>
                <Button fullWidth onClick={() => navigate('/home/text')} sx={{ textAlign: 'left', justifyContent: 'flex-start', color: '#ffffff' }}>Tin nhắn</Button>
                <Button fullWidth onClick={() => navigate('/home/admin/student')} sx={{ textAlign: 'left', justifyContent: 'flex-start', color: '#ffffff' }}>Sinh viên</Button>
                <Button fullWidth onClick={() => navigate('/home/admin/printer')} sx={{ textAlign: 'left', justifyContent: 'flex-start', color: '#ffffff' }}>Máy in</Button>
                <Button fullWidth onClick={() => navigate('/home/prints')} sx={{ textAlign: 'left', justifyContent: 'flex-start', color: '#ffffff' }}>Quy định</Button>
                <Button fullWidth onClick={() => navigate('/home/prints')} sx={{ textAlign: 'left', justifyContent: 'flex-start', color: '#ffffff' }}>Thống kê</Button>
            
            </Sidebar>

            <MainContent>
                <AppBar position="static" sx={{ width: '100%' }}>
                    <Toolbar sx={{ bgcolor: '#66B2FF' }}>
                        <Box sx={{ marginLeft: 'auto', display: 'flex', alignItems: 'center' }}>
                            <Button color="inherit">
                                <NotificationsIcon />
                            </Button>
                            <Button color="inherit">
                                <SettingsIcon />
                            </Button>
                            <Button color="inherit">
                                <AccountCircleIcon />
                            </Button>
                        </Box>
                    </Toolbar>
                </AppBar>
                <Typography variant="h4" sx={{ fontWeight: 'bold', marginBottom: 2, padding: 3 }}>
                    Quản lý máy in
                </Typography>
                <Box sx={{ flexGrow: 1, padding: 2 }}>
                    <Grid container spacing={3}>
                        {printers.map((printer) => (
                            <Grid item xs={12} sm={6} md={4} key={printer.id}>
                                <Card onClick={() => handleClickOpen(printer)} sx={{ cursor: 'pointer' }}>
                                    <CardContent sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center' }}>
                                        <img
                                            src={placeholderImage}
                                            alt="Printer"
                                            style={{ width: '50%', height: 'auto', marginBottom: '10px' }}
                                        />
                                        <Typography variant="h5" gutterBottom>
                                            {printer.name}
                                        </Typography>
                                        <Typography variant="body2" color="text.secondary">
                                            Tình trạng: {printer.status}
                                        </Typography>
                                        <Box sx={{width:'25vh'}}>
                                        <Typography variant="body2" color="text.secondary" sx={{ marginTop: 2 }}>
                                            Lượng giấy:
                                        </Typography>
                                        <Box sx={{ marginBottom: 1 }}>
                                            <LinearProgress
                                                variant="determinate"
                                                value={printer.paper}
                                                sx={{ backgroundColor: '#e0f7fa', '& .MuiLinearProgress-bar': { backgroundColor: '#4caf50' } }}
                                            />
                                        </Box>
                                        </Box>
                                        <Box sx={{width:'25vh'}}>
                                        <Typography variant="body2" color="text.secondary" sx={{ maxWidth: '100px' }}>
                                            Lượng mực:
                                        </Typography>
                                        <Box sx={{ }}>
                                            <LinearProgress
                                                variant="determinate"
                                                value={printer.ink}
                                                sx={{ backgroundColor: '#e0f7fa', '& .MuiLinearProgress-bar': { backgroundColor: '#2196f3' } }}
                                            />
                                        </Box>
                                        </Box>
                                    </CardContent>
                                </Card>
                            </Grid>
                        ))}
                    </Grid>

                    {/* Dialog for detailed printer information */}
                    <Dialog open={open} onClose={handleClose} fullWidth>
                        <DialogTitle >Thông tin chi tiết máy in</DialogTitle>
                        <DialogContent >
                            {selectedPrinter && (
                                <Grid container spacing={2} sx={{padding:3}}>
                                    <Grid item xs={6}>
                                        <TextField
                                            fullWidth
                                            label="Tên máy"
                                            value={selectedPrinter.name}
                                            variant="outlined"
                                            InputProps={{
                                                readOnly: true,
                                            }}
                                        />
                                    </Grid>
                                    <Grid item xs={6}>
                                        <TextField
                                            fullWidth
                                            label="Mã máy"
                                            value={`KX-${selectedPrinter.id}`}
                                            variant="outlined"
                                            InputProps={{
                                                readOnly: true,
                                            }}
                                        />
                                    </Grid>
                                    <Grid item xs={6}>
                                        <TextField
                                            fullWidth
                                            label="Tình trạng"
                                            value={selectedPrinter.status}
                                            variant="outlined"
                                            InputProps={{
                                                readOnly: true,
                                            }}
                                        />
                                    </Grid>
                                    <Grid item xs={6}>
                                        <TextField
                                            fullWidth
                                            label="Loại tệp có thể in"
                                            value="Mặc định"
                                            variant="outlined"
                                            InputProps={{
                                                readOnly: true,
                                            }}
                                        />
                                    </Grid>
                                    <Grid item xs={6}>
                                        <TextField
                                            fullWidth
                                            label="Ngày bắt đầu sử dụng"
                                            value={selectedPrinter.date}
                                            variant="outlined"
                                            InputProps={{
                                                readOnly: true,
                                            }}
                                        />
                                    </Grid>
                                    <Grid item xs={6}>
                                        <TextField
                                            fullWidth
                                            label="Lượng mực"
                                            value={`${selectedPrinter.ink}%`}
                                            variant="outlined"
                                            InputProps={{
                                                readOnly: true,
                                            }}
                                        />
                                    </Grid>
                                    <Grid item xs={6}>
                                        <TextField
                                            fullWidth
                                            label="Lượng giấy"
                                            value={`${selectedPrinter.paper}%`}
                                            variant="outlined"
                                            InputProps={{
                                                readOnly: true,
                                            }}
                                        />
                                    </Grid>
                                    <Grid item xs={6}>
                                        <TextField
                                            fullWidth
                                            label="Lịch sử in"
                                            value="10 tờ"
                                            variant="outlined"
                                            InputProps={{
                                                readOnly: true,
                                            }}
                                        />
                                    </Grid>
                                </Grid>
                            )}
                        </DialogContent>
                        <DialogActions>
                            <Button onClick={handleClose} color="primary">
                                Đóng
                            </Button>
                        </DialogActions>
                    </Dialog>
                </Box>
            </MainContent>
        </div>
    );
}

export default Home;