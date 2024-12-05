import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import SendIcon from '@mui/icons-material/Send'
import Box from '@mui/material/Box'
import Typography from '@mui/material/Typography'
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import React, { useState, useEffect } from 'react';
import LoginIcon from '@mui/icons-material/Login';
import SchoolIcon from '@mui/icons-material/School';
import TextField from '@mui/material/TextField';
import {useNavigate}  from 'react-router-dom';
import { styled} from '@mui/material';
import axios from 'axios'; 
import IconButton from '@mui/material/IconButton';
import InputAdornment from '@mui/material/InputAdornment';
import VisibilityIcon from '@mui/icons-material/Visibility';
import VisibilityOffIcon from '@mui/icons-material/VisibilityOff';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import NotificationsIcon from '@mui/icons-material/Notifications';
import MessageIcon from '@mui/icons-material/Message'; // Biểu tượng tin nhắn
import {  Grid, Card,CardContent, CardMedia } from '@mui/material';
import NoteIcon from '@mui/icons-material/Note';
import {  Popover, List, ListItem,ListItemIcon, ListItemText } from '@mui/material';
import PersonIcon from '@mui/icons-material/Person';
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';
import InfoIcon from '@mui/icons-material/Info';
import PrintIcon from '@mui/icons-material/Print';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
function Home() {

const navigate = useNavigate();

    const handleLogOut = (e) =>{
      localStorage.removeItem('CustomerUserID');
      navigate('/home');
  }
const CustomerUserID = localStorage.getItem('CustomerUserID');
const [CustomerInfo, setCustomerInfo] = useState('');

const [showPayTextBox, setShowPayTextBox] = useState(false);

const [anchorEl, setAnchorEl] = useState(null);
const [NoPage, setNoPage] = useState('');
const handleInfo = (event) => {
    navigate('/home/customer/info');
};
const handlePaymentLog = (event) => {
    navigate('/home/customer/paymentLog');
};
const handlePrintingLog = (event) => {
    navigate('/home/customer/printingLog');
};
const handlePay = (event) => {
    setShowPayTextBox(true);
};
const handleClick = (event) => {
    setAnchorEl(event.currentTarget); // Open menu
};
const handlePayPage = async () => {
    try {
        const response = await axios.post(`http://localhost:8081/student/${CustomerUserID}/buy-pages`,{
            NoPage
        });
        // Fetch additional info for each printer
        alert(response.data.message);
        } catch (error) {
            console.error("Error fetching printers:", error);
        } finally {
            }
};
const handleClose = () => {
    setAnchorEl(null); // Close menu
};
const handlePrinterClick =(id) => {
    navigate(`/home/customer/print/${id}`);
}
const fetchInfo = async () => {
    try {
        const response = await axios.get(`http://localhost:8081/student/${CustomerUserID}/info`);
        setCustomerInfo(response.data)
        // Fetch additional info for each printer
        } catch (error) {
            console.error("Error fetching printers:", error);
        } finally {
            }
    }

useEffect(() => {
        fetchInfo();
    }, []);
useEffect(() => {
        const handleKeyDown = (event) => {
            if (event.key === 'Escape') {
                // Tắt ô vuông khi nhấn phím Esc
                setShowPayTextBox(false);
            }
        };

        window.addEventListener('keydown', handleKeyDown);

        // Cleanup function để loại bỏ sự kiện khi component unmount
        return () => {
            window.removeEventListener('keydown', handleKeyDown);
        };
    }, []);

function ListPrinter() {
    const [printers, setPrinters] = useState([]);
    const [printerDetails, setPrinterDetails] = useState({});
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(true); 

    useEffect(() => {
        const fetchPrinters = async () => {
            try {
                const response = await axios.get(`http://localhost:8081/student/${CustomerUserID}/printers`);
                setPrinters(response.data); // Update state with the printer list

                // Fetch additional info for each printer
                const details = {};
                for (const printer of response.data) {
                    const detailResponse = await axios.get(`http://localhost:8081/admin/printer/info?id=${printer.roomName}`);
                    details[printer.id] = detailResponse.data; // Store the details using printer ID as the key
                }
                setPrinterDetails(details); // Update state with the printer details

            } catch (error) {
                console.error("Error fetching printers:", error);
                setError(error.message); // Set error message for display
            } finally {
                setLoading(false); // Set loading to false regardless of success or failure
            }
        };

        fetchPrinters();
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
<Box sx={{ maxWidth: 1200, margin: 'auto', padding: 2, border: '1px solid #ccc', borderRadius: 2, backgroundColor: '#f9f9f9', boxShadow: 1, marginTop: '20px' }}>
    <Typography variant="h6" component="h2" gutterBottom>
        Danh sách các máy in
    </Typography>
    <List>
        {printers.map((printer) => (
            <ListItem key={printer.id} sx={{ display: 'flex', flexDirection: 'column', marginBottom: 2 }}>
                <Button 
                    sx={{ display: 'flex', width: '100%', justifyContent: 'flex-start', padding: 2, textAlign: 'left' }} 
                    variant="outlined" 
                    onClick={() => handlePrinterClick(printer.roomName)} // Add your click handler
                >
                    <Box sx={{ display: 'flex', alignItems: 'center', flexGrow: 1 , marginRight : '100px' }}>
                        <img src="/Avatar.png" alt="Icon" width="120px" height="120px"  />
                        <ListItemText  
                            primary={`ID: ${printer.id}`} 
                            secondary={`Trạng thái: ${printer.status ? 'Available' : 'Not Available'}`} 
                        />
                    </Box>
                {printerDetails[printer.id] && (
                    <Box sx={{ display: 'flex', justifyContent: 'space-between', marginTop: 1 }}>
                        <Box sx={{ flex: 1 }}>
                            <Typography variant="subtitle2">
                                Tên máy: {printerDetails[printer.id].printerName}
                            </Typography>
                            <Typography variant="subtitle2">
                                Lượng mực: {printerDetails[printer.id].inkAmount}%
                            </Typography>
                            <Typography variant="subtitle2">
                                Số lượng giấy: {printerDetails[printer.id].pageAmount}
                            </Typography>
                        </Box>
                        <Box sx={{ flex: 1, width : '550px' }}>
                            <Typography variant="subtitle2">
                                Loại máy: {printerDetails[printer.id].firm}
                            </Typography>
                            <Typography variant="subtitle2">
                                Mô tả: {printerDetails[printer.id].description}
                            </Typography>
                            <Typography variant="subtitle2">
                               Hiệu năng: {printerDetails[printer.id].efficiency}%
                            </Typography>
                        </Box>
                    </Box>
                )}
                <span className={`printer-status ${printer.status ? 'available' : 'not-available'}`}>
                            <span 
                                style={{
                                    display: 'inline-block',
                                    width: '10px',
                                    height: '10px',
                                    borderRadius: '50%',
                                    backgroundColor: printer.status ? 'green' : 'red',
                                    transition: 'background-color 0.3s, transform 0.3s',
                                    // marginLeft: '10px',
                                }} 
                            />
                        </span>
                </Button>
            </ListItem>
        ))}
    </List>
</Box>
    );
}

  return (
    <div className="App" style={{ 
        // backgroundImage: 'url("/img2.jpg")', // Sử dụng đường dẫn từ thư mục public
        backgroundColor: '#F5F5F5',
        backgroundSize: 'cover',
        backgroundRepeat: 'no-repeat',
        backgroundPosition: 'center',
        minHeight: '100vh', // Sử dụng minHeight thay vì height
        display: 'flex',     // Sử dụng flexbox để tự động điều chỉnh chiều cao
        flexDirection: 'column', // Căn chỉnh theo chiều dọc
      }}>   

        <AppBar position="static">
                <Toolbar sx={{ bgcolor: '#66B2FF', display: 'flex', justifyContent: 'space-between' }}>
                  <Button>
                      <img src="/Avatar.png" alt="Icon" width="80" height="40" />
                      <Typography variant='h5' style={{ fontWeight: 'bold' }} align='left' sx={{ flexGrow: 1 }} color='#FFFFFF'>
                          Student Smart Printing Service
                      </Typography>
                  </Button>

                  <Box sx={{ display: 'flex', justifyContent: 'flex-end' }}>
                        <Button
                            variant='outlined' 
                            color='inherit' 
                            onClick={handlePay} 
                            sx={{ 
                                fontSize: '1.0rem', 
                                marginRight: '10px', 
                                color: '#000000', 
                                borderRadius: '9px', 
                                display: 'flex', 
                                alignItems: 'center', 
                                fontWeight: 'bold',
                            }}>
                            Mua giấy in
                        </Button>
                        <Button
                            variant='outlined' 
                            color='inherit' 
                            onClick={handleClick} 
                            sx={{ 
                                fontSize: '1.0rem', 
                                marginRight: '10px', 
                                color: '#000000', 
                                borderRadius: '9px', 
                                display: 'flex', 
                                alignItems: 'center', 
                                fontWeight: 'bold',
                            }}>
                            <PersonIcon/> {CustomerInfo ? `${CustomerInfo.firstName} ${CustomerInfo.lastName}` : 'Loading...'} <ArrowDropDownIcon/>
                        </Button>
                        <Menu
                            anchorEl={anchorEl}
                            open={Boolean(anchorEl)}
                            onClose={handleClose}
                            PaperProps={{
                                style: {
                                    width: anchorEl ? anchorEl.clientWidth : 'auto', // Match button width
                                },
                            }}
                        >
                            <MenuItem onClick={handleInfo}>
                                <ListItemIcon>
                                    <InfoIcon />
                                </ListItemIcon>
                                <Typography>Thông tin</Typography>
                            </MenuItem>
                            <MenuItem onClick={handlePrintingLog}>
                                <ListItemIcon>
                                    <PrintIcon />
                                </ListItemIcon>
                                <Typography>Lịch sử in</Typography>
                            </MenuItem>
                            <MenuItem onClick={handlePaymentLog}>
                                <ListItemIcon>
                                    <ShoppingCartIcon />
                                </ListItemIcon>
                                <Typography>Lịch sử mua</Typography>
                            </MenuItem>
                        </Menu>
                      <Button 
                          variant='outlined' 
                          color='inherit' 
                          onClick={handleLogOut} 
                          sx={{ 
                              fontSize: '1.0rem', 
                              marginRight: '10px', 
                              color: '#000000', 
                              borderRadius: '9px', 
                              display: 'flex', 
                              alignItems: 'center', 
                              fontWeight: 'bold',
                          }}
                      >
                          <LoginIcon fontSize='small' style={{ textTransform: 'none', marginRight: '5px', fontWeight: 'bold' }} /> 
                          ĐĂNG XUẤT
                      </Button>
                  </Box>
                </Toolbar>
            </AppBar>
            <ListPrinter/>
            {showPayTextBox && (
                    <>
                    <div
                    style={{
                        position: 'fixed',
                        top: 0,
                        left: 0,
                        right: 0,
                        bottom: 0,
                        backgroundColor: 'rgba(255, 255, 255, 0.2)', // Màu nền overlay (có thể điều chỉnh)

                        display: 'flex',
                        justifyContent: 'center',
                        alignItems: 'center',
                        backdropFilter: 'blur(8px)', // Sử dụng backdrop-filter để làm mờ nền
                    }}
                    />
                        <div style={{ 
                            position: 'absolute',
                            top: '50%',
                            left: '50%',
                            transform: 'translate(-50%, -50%)',
                            backgroundColor : '#B3D9FF',
                            backgroundSize: 'cover',
                            padding: '100px 100px',
                            borderRadius: '10px',
                            boxShadow: '0px 0px 10px rgba(0, 0, 0, 0.1)',
                        }}>
                        <Typography variant='h5' style={{fontWeight:'bold'}}>Mua giấy in</Typography>
                        <Typography gutterBottom variant='h5'></Typography>
                        <TextField label='Số lượng giấy cần mua:' variant='standard' 
                            fullWidth 
                            required 
                            onChange={(e)=>setNoPage(e.target.value)} 
                            InputLabelProps={{style: { color: '#000000' }}}/>
                        {/* <TextField
                            label="Mật khẩu:"
                            variant="standard"
                            fullWidth
                            required
                            type={showPassword ? 'text' : 'password'}
                            onChange={(e) => setAdminPassword(e.target.value)}
                            InputProps={{
                                endAdornment: (
                                <InputAdornment position="end">
                                    <IconButton
                                    onClick={() => setShowPassword(!showPassword)}
                                    edge="end"
                                    >
                                    {showPassword ? <VisibilityIcon /> : <VisibilityOffIcon />}
                                    </IconButton>
                                </InputAdornment>
                                ),
                            }}
                            InputLabelProps={{
                                style: { color: '#000000' }, // Thay đổi màu sắc của nhãn ở đây
                            }}
                            /> */}
                        <Typography gutterBottom variant='h2'></Typography>
                        <Button startIcon={<SendIcon/> } variant='contained' color={'inherit'} 
                        onClick={handlePayPage} 
                        style={{ 
                            fontSize: '1.0rem', // Kích thước của nút
                            marginRight: '10px', // Khoảng cách so với mép màn hình bên phải
                            color: '#000000', // Màu chữ của nút
                            backgroundColor: '#87CEEB', // Màu nền của nút
                            border: '0px solid #87CEEB', // Viền nằm trong nút
                            borderRadius: '9px', // Bo tròn góc của viền
                            padding: '7px 25px',
                            display: 'flex', // Sử dụng flexbox để căn chỉnh
                            alignItems: 'center', 
                            fontWeight: 'bold',
                        }}>mua</Button>
                        </div>
                    </>

                    )}
    </div>
  );
}

export default Home;
 