{
	"info": {
		"_postman_id": "bcdbe132-71a8-4a4d-91f9-693abbbc6787",
		"name": "Smart_printing",
		"schema": "https://schema.getpostman.com/json/collection/v2.0.0/collection.json",
		"_exporter_id": "33929895",
		"_collection_link": "https://www.postman.com/dacnpm-5156/workspace/smart-printing/collection/33929895-bcdbe132-71a8-4a4d-91f9-693abbbc6787?action=share&source=collection_link&creator=33929895"
	},
	"item": [
		{
			"name": "Admin",
			"item": [
				{
					"name": "Thêm giá giấy",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "\r\n",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8081/admin/unit-price?price=3000",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"admin",
								"unit-price"
							],
							"query": [
								{
									"key": "price",
									"value": "3000"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "Thêm loại file",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n    \"fileTypeName\": \"pdf\"\r\n}\r\n",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": "http://localhost:8081/admin/add-file-type"
					},
					"response": []
				},
				{
					"name": "Thêm máy in",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n    \"printerName\": \"Canon PIXMA\",\r\n    \"inkAmount\": 100,\r\n    \"pageAmount\": 500,\r\n    \"firm\": \"Canon\",\r\n    \"description\": \"Máy in đa năng chất lượng cao\",   \r\n    \"efficiency\": 85,\r\n    \"status\": true\r\n}\r\n",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8081/admin/addprinter?room-name=Phòng 320&building-name=BuildingA&campus-name=Campus1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"admin",
								"addprinter"
							],
							"query": [
								{
									"key": "room-name",
									"value": "Phòng 320"
								},
								{
									"key": "building-name",
									"value": "BuildingA"
								},
								{
									"key": "campus-name",
									"value": "Campus1"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "Thêm tài khoản admin",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n    \"firstName\": \"Đạt\",\r\n    \"lastName\": \"Đỗ Tuấn\",\r\n    \"username\": \"đatotuan\",\r\n    \"password\": \"123\",\r\n    \"studentNumber\": 2210678\r\n}\r\n",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": "http://localhost:8081/admin/register"
					},
					"response": []
				},
				{
					"name": "Lấy danh sách máy in",
					"request": {
						"method": "GET",
						"header": [],
						"url": "http://localhost:8081/admin/printer"
					},
					"response": []
				},
				{
					"name": "Lấy trạng thái máy in",
					"request": {
						"method": "GET",
						"header": [],
						"url": "http://localhost:8081/admin/printer-stat"
					},
					"response": []
				},
				{
					"name": "Lấy thông tin 1 máy in",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8081/admin/printer/info?id=67458fc142ce0520fdb53204",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"admin",
								"printer",
								"info"
							],
							"query": [
								{
									"key": "id",
									"value": "67458fc142ce0520fdb53204"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "Lấy danh sách các cơ sở có máy in",
					"request": {
						"method": "GET",
						"header": [],
						"url": "http://localhost:8081/admin/campus"
					},
					"response": []
				},
				{
					"name": "Lấy danh sách các tòa có máy in",
					"request": {
						"method": "GET",
						"header": [],
						"url": "http://localhost:8081/admin/building"
					},
					"response": []
				},
				{
					"name": "Lấy danh sách các phòng có máy in",
					"request": {
						"method": "GET",
						"header": [],
						"url": "http://localhost:8081/admin/room"
					},
					"response": []
				},
				{
					"name": "Lấy lịch sử in ấn",
					"request": {
						"method": "GET",
						"header": [],
						"url": "http://localhost:8081/admin/printing-logs"
					},
					"response": []
				},
				{
					"name": "Lấy danh sách lịch sử in",
					"request": {
						"method": "GET",
						"header": [],
						"url": "http://localhost:8081/admin/payment-logs"
					},
					"response": []
				},
				{
					"name": "Lấy cấu hình của dự án",
					"request": {
						"method": "GET",
						"header": [],
						"url": "http://localhost:8081/admin/config"
					},
					"response": []
				},
				{
					"name": "Đăng nhập tài khoản admin",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n    \"username\": \"đatotun\",\r\n    \"password\": \"13\"\r\n}\r\n",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": "http://localhost:8081/admin/login"
					},
					"response": []
				},
				{
					"name": "Thêm kích cỡ file",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n    \"maxFileSize\": 20.5,\r\n    \"value\": 20.5\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8081/admin/file-size?size=20.5",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"admin",
								"file-size"
							],
							"query": [
								{
									"key": "size",
									"value": "20.5"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "Thêm phân bổ trang",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "    {\r\n        \"id\": \"674594dfec3b81670515e8da\",\r\n        \"semester\": 2024,\r\n        \"year\": 2024,\r\n        \"allocatedDate\": \"2024-11-25\",\r\n        \"numOfPage\": 1000,\r\n        \"status\": true\r\n    }",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": "http://localhost:8081/admin/add-page-allocation"
					},
					"response": []
				},
				{
					"name": "Lấy phân bổ trang",
					"request": {
						"method": "GET",
						"header": [],
						"url": "http://localhost:8081/admin/page-allocation"
					},
					"response": []
				},
				{
					"name": "Đăng kí tài khoản admin",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n    \"firstName\": \"abc\",\r\n    \"lastName\":\"ghk\",\r\n    \"username\": \"test\",\r\n    \"password\": \"13\"\r\n}\r\n",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": "http://localhost:8081/admin/register"
					},
					"response": []
				},
				{
					"name": "Kiểm tra số liệu lịch sử đặt hàng",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8081/admin/statistics/request-by-printer?from=2024-11&to=2024-12",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"admin",
								"statistics",
								"request-by-printer"
							],
							"query": [
								{
									"key": "from",
									"value": "2024-11"
								},
								{
									"key": "to",
									"value": "2024-12"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "Lấy doanh thu của tổng tất cả máy in theo tháng",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8081/admin/statistics/profit-by-month?from=2024-11&to=2024-12",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"admin",
								"statistics",
								"profit-by-month"
							],
							"query": [
								{
									"key": "from",
									"value": "2024-11"
								},
								{
									"key": "to",
									"value": "2024-12"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "Lấy doanh thu từ tháng nào đến tháng nào của 1 máy in",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8081/admin/statistics/profit-by-month/printer?from=2024-11&to=2024-12&printerId=674d7eae0b1a21213a493b4f",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"admin",
								"statistics",
								"profit-by-month",
								"printer"
							],
							"query": [
								{
									"key": "from",
									"value": "2024-11"
								},
								{
									"key": "to",
									"value": "2024-12"
								},
								{
									"key": "printerId",
									"value": "674d7eae0b1a21213a493b4f"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "Lấy số lượng giấy đã in từ tất cả các máy in theo tháng",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8081/admin/statistics/paper-usage-by-month?from=2024-10&to=2024-12",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"admin",
								"statistics",
								"paper-usage-by-month"
							],
							"query": [
								{
									"key": "from",
									"value": "2024-10"
								},
								{
									"key": "to",
									"value": "2024-12"
								}
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "Student",
			"item": [
				{
					"name": "Đăng kí tài khoản sinh viên",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n    \"firstName\": \"Nguyen\",\r\n    \"lastName\": \"Van A\",\r\n    \"username\": \"nguyen\",\r\n    \"password\": \"securepassword123\",\r\n    \"email\": \"john@example.com\",\r\n    \"studentNumber\": \"2958483\"\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": "http://localhost:8081/register"
					},
					"response": []
				},
				{
					"name": "Thao tác in",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "[\r\n    {\r\n        \"fileName\": \"test_document.pdf\",\r\n        \"numOfPages\": 10,\r\n\t\t\"size\" : 2.6,\r\n        \"numOfCopies\": 2,\r\n        \"isHori\": false,\r\n        \"isDoubleSided\": true,\r\n        \"pageSize\": \"A4\"\r\n    }\r\n]\r\n",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8081/student/67458c83fb4e2e0588eb4aa5/print?printer-id=67458fc142ce0520fdb53204",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"student",
								"67458c83fb4e2e0588eb4aa5",
								"print"
							],
							"query": [
								{
									"key": "printer-id",
									"value": "67458fc142ce0520fdb53204"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "Mua giấy in",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"numOfPages\": 1000,\r\n  \"pageSize\": \"A3\"\r\n}\r\n",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": "http://localhost:8081/student/67458c83fb4e2e0588eb4aa5/buy-pages"
					},
					"response": []
				},
				{
					"name": "Lấy lịch sử mua giấy của 1 sinh viên",
					"request": {
						"method": "GET",
						"header": [],
						"url": "http://localhost:8081/student/67458c83fb4e2e0588eb4aa5/payment-logs"
					},
					"response": []
				},
				{
					"name": "Lấy lịch sử in của 1 sinh viên",
					"request": {
						"method": "GET",
						"header": [],
						"url": "http://localhost:8081/student/67458c83fb4e2e0588eb4aa5/printing-logs"
					},
					"response": []
				},
				{
					"name": "Lấy thông tin của sinh viên",
					"request": {
						"method": "GET",
						"header": [],
						"url": "http://localhost:8081/student/67458c83fb4e2e0588eb4aa5/info"
					},
					"response": []
				},
				{
					"name": "Đăng nhập vào tài khoản sinh viên",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n    \"username\": \"nguyen\",\r\n    \"password\": \"u3\"\r\n}\r\n\r\n",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": "http://localhost:8081/login"
					},
					"response": []
				},
				{
					"name": "Lấy danh sách máy in cho nhân viên",
					"request": {
						"method": "GET",
						"header": [],
						"url": "http://localhost:8081/student/67458c83fb4e2e0588eb4aa5/printers"
					},
					"response": []
				},
				{
					"name": "chọn file để up",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "formdata",
							"formdata": [
								{
									"key": "file",
									"type": "file",
									"src": "/C:/Users/ACER/Downloads/Chi nhánh.pdf"
								}
							]
						},
						"url": "http://localhost:8081/student/67458c83fb4e2e0588eb4aa5/upload"
					},
					"response": []
				}
			]
		}
	]
}