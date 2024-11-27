// package com.se.ssps.server.service.user;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;


// import com.se.ssps.server.entity.user.User;

// import com.se.ssps.server.repository.user.UserRepository;

// @Service
// public class UserServiceImpl implements UserService {

//     @Autowired
//     UserRepository userRepository;

//     @Override
//     public User findUser(String username) {
//         return userRepository.findByUsername(username);
//     }

//     // Hàm xử lý đăng ký
//     public User registerUser(User user) throws Exception {
//         // Kiểm tra nếu username đã tồn tại
//         if (userRepository.findByUsername(user.getUsername()) != null) {
//             throw new Exception("Username already exists!");
//         }

//         // Thêm người dùng mới vào cơ sở dữ liệu
//         return userRepository.save(user);
//     }




// }
