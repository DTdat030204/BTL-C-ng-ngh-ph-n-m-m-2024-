import React from 'react';
import { AppBar, Toolbar, Button, Typography, Box, Grid, Card, CardContent, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import SchoolIcon from '@mui/icons-material/School';
import PrintIcon from '@mui/icons-material/Print';
import PaperIcon from '@mui/icons-material/Feed';
import NotificationsIcon from '@mui/icons-material/Notifications'; // Notification icon
import SettingsIcon from '@mui/icons-material/Settings'; // Settings icon
import AccountCircleIcon from '@mui/icons-material/AccountCircle'; // Account icon
import { styled } from '@mui/material/styles';

const Sidebar = styled('div')({
    width: '150px',
    backgroundColor: '#66B2FF',
    height: '100%',
    position: 'fixed',
    padding: '20px',
    marginTop:'20px',
    
});

const MainContent = styled('div')({
    marginLeft: '170px',
    padding: '20px',
    flexGrow: 1,
});

const Notice = styled('div')({
    marginLeft: '50px,'
});

function Home() {
    const navigate = useNavigate();

    return (
        <div style={{ display: 'flex', height: '100vh' }}>
            <Sidebar>
                <Button fullWidth onClick={() => navigate('/')} sx={{ display: 'flex', justifyContent: 'flex-start' }}>
                    
                    <img src="/Avatar.png" alt="Icon" width="60" height="30" />
                    <Typography variant='h5' style={{ fontWeight: 'bold' }} align='left' sx={{ flexGrow: 1 }} color='#FFFFFF'> SSPS </Typography>
                </Button>
                <Button fullWidth onClick={() => navigate('/home/admin')} sx={{ textAlign: 'left', justifyContent: 'flex-start', color: '#ffffff' }} >Trang chủ</Button>
                <Button fullWidth onClick={() => navigate('/home/text')} sx={{ textAlign: 'left', justifyContent: 'flex-start' , color: '#ffffff'}}>Tin nhắn</Button>
                <Button fullWidth onClick={() => navigate('/home/admin/student')} sx={{ textAlign: 'left', justifyContent: 'flex-start' , color: '#ffffff'}}>Sinh viên</Button>
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

                <Box sx={{ flexGrow: 1, padding: 2 }}>
                    <Notice>
                      <Grid container spacing={3} justifyContent="space-between">
                          <Grid item xs={12} sm={4}>
                              <Card>
                                  <CardContent>
                                      <Typography variant="h6" color="text.secondary" gutterBottom>
                                          Lượt in hôm nay
                                      </Typography>
                                      <Box display="flex" alignItems="center">
                                          <Typography variant="h4" style={{ marginRight: '8px' }}>50</Typography>
                                          <SchoolIcon fontSize="large" />
                                      </Box>
                                      
                                      
                                  </CardContent>
                              </Card>
                          </Grid>
                          <Grid item xs={12} sm={4}>
                              <Card>
                                  <CardContent>
                                      <Typography variant="h6" color="text.secondary" gutterBottom>
                                          Số máy có vấn đề
                                      </Typography>
                                      <Box display="flex" alignItems="center">
                                          <Typography variant="h4" style={{ marginRight: '8px' }}>5</Typography>
                                          <PrintIcon fontSize="large" />
                                      </Box>
                                  </CardContent>
                              </Card>
                          </Grid>
                          <Grid item xs={12} sm={4}>
                              <Card>
                                  <CardContent>
                                      <Typography variant="h6" color="text.secondary" gutterBottom>
                                          Lượng giấy sử dụng
                                      </Typography>
                                      <Box display="flex" alignItems="center">
                                          <Typography variant="h4" style={{ marginRight: '8px' }}>300</Typography>
                                          <PaperIcon fontSize="large" />
                                      </Box>
                                  </CardContent>
                              </Card>
                          </Grid>
                      </Grid>
                    </Notice>
                    <TableContainer component={Paper} sx={{ marginTop: 3 }}>
                        <Table>
                            <TableHead>
                                <TableRow>
                                    <TableCell>Mã máy</TableCell>
                                    <TableCell>Trạng thái</TableCell>
                                    <TableCell>Số lượng in</TableCell>
                                    <TableCell>Ngày sử dụng</TableCell>
                                    <TableCell>Ghi chú</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                <TableRow>
                                    <TableCell>H1 - 201</TableCell>
                                    <TableCell>Đang sử dụng</TableCell>
                                    <TableCell>15</TableCell>
                                    <TableCell>20/11/2024</TableCell>
                                    <TableCell>Ghi chú 1</TableCell>
                                </TableRow>
                                <TableRow>
                                    <TableCell>A1 - 101</TableCell>
                                    <TableCell>Đang chờ</TableCell>
                                    <TableCell>10</TableCell>
                                    <TableCell>21/11/2024</TableCell>
                                    <TableCell>Ghi chú 2</TableCell>
                                </TableRow>
                                {/* Add more rows as needed */}
                            </TableBody>
                        </Table>
                    </TableContainer>
                </Box>
            </MainContent>
        </div>
    );
}

export default Home;