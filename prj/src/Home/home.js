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
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import {  Grid, Card,CardContent, CardMedia } from '@mui/material';
import NoteIcon from '@mui/icons-material/Note';
import {  Popover, List, ListItem, ListItemText } from '@mui/material';

function Home() {

    const [showPassword, setShowPassword] = useState(false);
    // Đăng nhập
    const [showUserTextBox, setShowUserTextBox] = useState(false);
    const [showAdminLoginTextBox, setShowAdminLoginTextBox] = useState(false);
    const [showCustomersLoginTextBox, setShowCustomersLoginTextBox] = useState(false);
    // Tài khoản
    const [Admin_name,setAdminName]=useState('')
    const [Admin_password, setAdminPassword] = useState('')
    const [Customers_name,setCustomersName]=useState('')
    const [Customers_password, setCustomersPassword] = useState('')
    // Đăng kí
    const [showSignUpTextBox, setShowSignUpTextBox] = useState(false);
    const [signUpName, setSignUpName] = useState('');
    const [signUpLastName, setSignUpLastName] = useState('');
    const [signUpUsername, setSignUpUsername] = useState('');
    const [signUpPassword, setSignUpPassword] = useState('');
    const [signUpEmail, setSignUpEmail] = useState('');
    const [signUpStudentNumber, setSignUpStudentNumber] = useState('');
    const [notiSignUpSuccess, setNotiSignUpSuccess] = useState(false);
    const [notiSignUpFail, setNotiSignUpFail] = useState(false);
    const navigate = useNavigate();

    const UserButton = styled (Button)
    ({
      fontSize: '1.0rem', // Kích thước của nút
        color: '#380B61', // Màu chữ của nút
        border: '0px solid #380B61', // Viền nằm trong nút
        borderRadius: '9px', // Bo tròn góc của viền
        padding: '30px 200px',
        display: 'flex', // Sử dụng flexbox để căn chỉnh
        alignItems: 'center', 
        fontWeight: 'bold',
        '&: hover': { backgroundColor: '#87CEEB'}
        
  })

    const handleLogInClick = () => {
      // Hiển thị ô vuông khi bấm nút SignUp
        setShowUserTextBox(true);
        setShowAdminLoginTextBox(false);
        setShowCustomersLoginTextBox(false);
    };

    const handleAdminLoginClick = () => {
        // Hiển thị ô vuông khi bấm nút SignUp
        setShowAdminLoginTextBox(true);
        setShowUserTextBox(false);
        setShowCustomersLoginTextBox(false);
    };
    const handleCustomersLoginClick = () => {
      // Hiển thị ô vuông khi bấm nút SignUp
      setShowCustomersLoginTextBox(true);
      setShowUserTextBox(false);
      setShowAdminLoginTextBox(false);
    };
    const handleSignUp = () => {
      setShowSignUpTextBox(true);
      setShowUserTextBox(false);
      setShowAdminLoginTextBox(false);
      setShowCustomersLoginTextBox(false);
    } 

    async function authenticate(username, password) {
      try {
          const response = await axios.post('http://localhost:8081/login', {
              username,
              password,
          });
  
          const { status, message, data } = response.data;
  
          if (status === 'success') {
              return { success: true, message, data }; // Return successful response
          } else {
              throw new Error(message || 'Incorrect password.'); // Use API message
          }
      } catch (error) {
          console.log("Authentication failed");
  
          let errorMessage = 'An error occurred during authentication.';
  
          if (error.response) {
              const { status, data } = error.response;
              if (status === 404) {
                  errorMessage =  (data.message || 'No additional information');
              } else if (status === 401) {
                  errorMessage = 'Authentication failed: ' + (data.message || 'No additional information');
              } else {
                  errorMessage = data.message || errorMessage;
              }
          } else if (error.request) {
              errorMessage = 'Request error: ' + error.message;
          } else {
              errorMessage = error.message;
          }
  
          return { success: false, message: errorMessage }; // Return the error message
      }
  }
  async function authenticate_Admin(username, password) {
    try {
        const response = await axios.post('http://localhost:8081/admin/login', {
            username,
            password,
        });

        const { status, message } = response.data;

        if (status === 'success') {
            return { success: true, message }; // Return successful response
        } else {
            throw new Error(message || 'Incorrect password.'); // Use API message
        }
    } catch (error) {
        console.log("Authentication failed");

        let errorMessage = 'An error occurred during authentication.';

        if (error.response) {
            const { status, data } = error.response;
            if (status === 404) {
                errorMessage =  (data.message || 'No additional information');
            } else if (status === 401) {
                errorMessage = data.message || 'Authentication failed: Incorrect password.'; // Use the API message
            } else {
                errorMessage = data.message || errorMessage;
            }
        } else if (error.request) {
            errorMessage = 'Request error: ' + error.message;
        } else {
            errorMessage = error.message;
        }

        return { success: false, message: errorMessage }; // Return the error message
    }
}
    async function registerUser(firstName, lastName, username, password, email, studentNumber) {
      try {
          const response = await axios.post('http://localhost:8081/register', {
              firstName,
              lastName,
              username,
              password,
              email,
              studentNumber,
          });
  
          const user = response.data; // Assuming the response is the user object

          // Check if user data is returned
          if (user && user.id) {
              return { success: true, user }; // Return the user object
          } else {
              throw new Error('User data is not valid');
          }
      } catch (error) {
          console.error("Registration failed");
  
          let errorMessage = 'An error occurred during registration.';
          if (error.response) {
              const { status, data } = error.response;
              if (status === 500 && data.message) {
                  errorMessage = data.message; // Use the message from the server
              } else {
                  errorMessage = data.message || 'An error occurred';
              }
          } else {
              errorMessage = error.message;
          }
  
          return { success: false, message: errorMessage };
      }
  }


  const handleAdminDangNhap = async () => {
    if (Admin_name && Admin_password) {
        const result = await authenticate_Admin(Admin_name, Admin_password);
        
        if (result.success) {
            // AdminLoginSuccessPage(result.data.id); // Uncomment and use if needed
            navigate('/home/admin'); // Redirect to admin home page
        } else {
            console.error('Authentication failed:', result.message);
            alert(result.message); // Show the error message to the user
        }
    } else {
        alert("Bạn chưa điền đầy đủ dữ liệu!"); // Notify user about missing data
        console.log("Thiếu dữ liệu");
    }
};

    const handleCustomersDangNhap = (e) => {
      e.preventDefault(); // Prevent default form submission if applicable
  
      if (Customers_name && Customers_password) {
          authenticate(Customers_name, Customers_password)
              .then(result => {
                  if (result.success) {
                      CustomerLoginSuccessPage(result.data.id); // Pass user ID or other data
                      navigate('/home/customer'); // Redirect to customer home page
                  } else {
                      console.error('Authentication failed:', result.message);
                      alert(result.message); // Show the error message from API
                  }
              })
              .catch(error => {
                  console.error('An unexpected error occurred:', error);
                  alert('An unexpected error occurred. Please try again.'); // Generic error alert
              });
      } else {
          alert("Bạn chưa điền đầy đủ dữ liệu!"); // Notify user about missing data
          console.log("Thiếu dữ liệu");
      }
  };
    const handleSubmitSignUp = (e) => {
      // e.preventDefault(); // Prevent the default form submission

      if (signUpName && signUpLastName && signUpUsername && signUpPassword && signUpEmail && signUpStudentNumber) {
          registerUser(signUpName, signUpLastName, signUpUsername, signUpPassword, signUpEmail, signUpStudentNumber)
              .then(result => {
                  if (result.success) {
                      alert('Đăng kí thành công!');
                      // Reset form fields
                      setSignUpName('');
                      setSignUpLastName('');
                      setSignUpUsername('');
                      setSignUpPassword('');
                      setSignUpEmail('');
                      setSignUpStudentNumber('');
                      setNotiSignUpSuccess(true);
                  } else {
                      setNotiSignUpFail(true);
                  }
              })
              .catch(error => {
                  console.error('An unexpected error occurred:', error);
              });
      } else {
          alert("Bạn chưa điền đầy đủ dữ liệu!");
          console.log("Thiếu dữ liệu");
      }
  };

  
    // Trong trang đăng nhập
    function AdminLoginSuccessPage( AdminUserID ) {
      // Lưu userId vào localStorage
      localStorage.setItem('AdminUserID', AdminUserID);
      // Chuyển hướng đến trang làm việc
    }
    function CustomerLoginSuccessPage( CustomerUserID ) {
      // Lưu userId vào localStorage
      localStorage.setItem('CustomerUserID', CustomerUserID);
      // Chuyển hướng đến trang làm việc
    }

  
    useEffect(() => {
        const handleKeyDown = (event) => {
            if (event.key === 'Escape') {
                // Tắt ô vuông khi nhấn phím Esc
                setShowUserTextBox(false);
                setShowAdminLoginTextBox(false);
                setShowCustomersLoginTextBox(false);
                setShowSignUpTextBox(false);
            }
        };

        window.addEventListener('keydown', handleKeyDown);

        // Cleanup function để loại bỏ sự kiện khi component unmount
        return () => {
            window.removeEventListener('keydown', handleKeyDown);
        };
    }, []);

//   const handleCardClick = (id) => {
//     // Chuyển hướng đến trang thông tin chi tiết của sản phẩm
//     // Giả sử bạn đang sử dụng react-router để điều hướng
//     navigate(`/chi_tiet/${id}`);
// };

  return (
    <div className="App" style={{ 
        // backgroundImage: 'url("/home.png")', // Sử dụng đường dẫn từ thư mục public
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
                          onClick={handleSignUp} 
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
                          <NoteIcon fontSize='small' style={{ textTransform: 'none', marginRight: '5px', fontWeight: 'bold' }} /> 
                          ĐĂNG KÍ
                      </Button>
                      <Button 
                          variant='outlined' 
                          color='inherit' 
                          onClick={handleLogInClick} 
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
                          ĐĂNG NHẬP
                      </Button>
                  </Box>
                </Toolbar>
            </AppBar>
            <img src="/home2.png" alt="Icon" width="auto" height="667px" />
    {showUserTextBox && (
<>
      <div
          style={{
            position: 'fixed',
            top: 0,
            left: 0,
            right: 0,
            bottom: 0,
            backgroundColor: 'rgba(255, 255, 255, 0.2)', // Màu nền overlay (có thể điều chỉnh)
            zIndex: 0,
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            backdropFilter: 'blur(8px)', // Sử dụng backdrop-filter để làm mờ nền
          }}
        />
        <div style={{ 
          position: 'absolute',
          border: '0px solid #380B61', // Viền nằm trong nút
          top: '50%',
          left: '50%',
          transform: 'translate(-50%, -50%)',
          backgroundColor : '#B3D9FF',
          backgroundSize: 'cover',
          padding: '50px',
          borderRadius: '10px',
          boxShadow: '0px 0px 10px rgba(0, 0, 0, 0.1)',
          
        }}>
          <Stack>
                <Typography style={{fontWeight: 'bold'}} variant='h5'>ĐĂNG NHẬP</Typography>
                <Typography variant='h2' gutterBottom></Typography>
                <Typography variant='h2' gutterBottom></Typography>

                <UserButton onClick={handleAdminLoginClick} gutterBottom > Quản Lý </UserButton>
                <UserButton onClick={handleCustomersLoginClick} gutterBottom  > Khách Hàng </UserButton>
                <Button 
                    onClick={handleSignUp} // Hàm xử lý sự kiện đăng ký
                    style={{ 
                        fontSize: '0.8rem', 
                        marginTop: '10px', 
                        textTransform: 'none',
                        color: 'inherit', // Màu mặc định
                        backgroundColor: 'transparent', // Không có màu nền
                        border: 'none', // Không có viền
                        transition: 'color 0.3s', // Hiệu ứng chuyển màu
                        padding: 0, // Bỏ padding nếu cần
                    }} 
                    onMouseOver={(e) => e.currentTarget.style.color = 'red'} // Đổi màu khi hover
                    onMouseOut={(e) => e.currentTarget.style.color = 'inherit'} // Đổi lại màu khi không hover
                >
                    Bạn chưa có tài khoản ?
                </Button>
            </Stack>      
        </div>
        </>
    )
    }

    {showAdminLoginTextBox && (
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
          <Typography variant='h5' style={{fontWeight:'bold'}}>Quản Lý</Typography>
          <Typography gutterBottom variant='h5'></Typography>
          <TextField label='Tên tài khoản:' variant='standard' 
              fullWidth 
              required 
              onChange={(e)=>setAdminName(e.target.value)} 
              InputLabelProps={{style: { color: '#000000' }}}/>
          <TextField
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
            />
          <Typography gutterBottom variant='h2'></Typography>
          <Button startIcon={<SendIcon/> } variant='contained' color={'inherit'} onClick={handleAdminDangNhap} style={{ 
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
          }}>Đăng Nhập</Button>
        </div>
      </>

    )}

    {showCustomersLoginTextBox &&(
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
            <Typography variant='h5' style={{fontWeight:'bold'}}>Khách hàng</Typography>
            <Typography gutterBottom variant='h5'></Typography>
            <TextField label='Tên tài khoản:' variant='standard' 
              fullWidth 
              required 
              onChange={(e)=>setCustomersName(e.target.value)} 
              InputLabelProps={{style: { color: '#000000' }}}/>
            <TextField
                  label="Mật khẩu:"
                  variant="standard"
                  fullWidth
                  required
                  type={showPassword ? 'text' : 'password'}
                  onChange={(e) => setCustomersPassword(e.target.value)}
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
                />
            <Typography gutterBottom variant='h2'></Typography>
            <Button startIcon={<SendIcon/> } variant='contained' color={'inherit'} onClick={handleCustomersDangNhap} style={{ 
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
            }}>Đăng Nhập</Button>
        </div>
</>
    )}
    {showSignUpTextBox &&(
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
                <Typography variant='h5' style={{fontWeight:'bold'}}>Đăng kí tài khoản</Typography>
                <Typography gutterBottom variant='h5'></Typography>
                <TextField label='Họ:' variant='standard' 
                      fullWidth 
                      required 
                      onChange={(e)=>setSignUpName(e.target.value)} 
                      InputLabelProps={{style: { color: '#000000' }}}/>
                  <TextField label='Tên:' variant='standard' 
                      fullWidth 
                      required 
                      onChange={(e)=>setSignUpLastName(e.target.value)} 
                      InputLabelProps={{style: { color: '#000000' }}}/>
                  <TextField label='Email:' variant='standard' 
                      fullWidth 
                      required 
                      onChange={(e)=>setSignUpEmail(e.target.value)} 
                      InputLabelProps={{style: { color: '#000000' }}}/>
                  <TextField label='Mã số sinh viên:' variant='standard' 
                      fullWidth 
                      required 
                      onChange={(e)=>setSignUpStudentNumber(e.target.value)} 
                      InputLabelProps={{style: { color: '#000000' }}}/>
                <TextField label='Tên tài khoản:' variant='standard' 
                      fullWidth 
                      required 
                      onChange={(e)=>setSignUpUsername(e.target.value)} 
                      InputLabelProps={{style: { color: '#000000' }}}/>
                <TextField
                      label="Mật khẩu:"
                      variant="standard"
                      fullWidth
                      required
                      type={showPassword ? 'text' : 'password'}
                      onChange={(e) => setSignUpPassword(e.target.value)}
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
                    />
                <Typography gutterBottom variant='h2'></Typography>
                <Button startIcon={<SendIcon/> } variant='contained' color={'inherit'} onClick={handleSubmitSignUp} style={{ 
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
                }}>Đăng Kí</Button>
            </div>
    </>
    )}
        {/* {Noti_SignUp_Fail &&(
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
                <Typography variant='h5' style={{fontWeight:'bold'}}>Đăng kí thất bại. Tài khoản đã tồn tại.</Typography>
            </div>
    </>
    )} */}
    </div>
  );
}

export default Home;
 