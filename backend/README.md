# Backend API - E-Commerce Admin Dashboard

Node.js/Express backend for the e-commerce admin dashboard.

## 📁 Backend Structure (To Be Created)

```
backend/
├── routes/              # API endpoints
├── controllers/         # Request handlers
├── models/             # Database schemas
├── middleware/         # Auth, validation, etc.
├── config/             # Database, environment config
├── server.js          # Server entry point
└── package.json       # Dependencies
```

## 🚀 Setup Instructions

### 1. Initialize Backend Project

```bash
npm init -y
npm install express mongoose jsonwebtoken bcryptjs dotenv cors
npm install --save-dev nodemon
```

### 2. Create Basic Structure

```bash
mkdir routes controllers models middleware config
touch server.js .env
```

### 3. Create `package.json` Scripts

```json
{
  "scripts": {
    "dev": "nodemon server.js",
    "start": "node server.js"
  }
}
```

## 🔐 Required API Endpoints

### Authentication
```
POST /api/auth/login
- Body: { email, password }
- Response: { token, employee: { id, name, email, role, department } }

POST /api/auth/logout
- Headers: { Authorization: "Bearer token" }
```

### Orders
```
GET /api/orders
GET /api/orders/:id
POST /api/orders
PUT /api/orders/:id
DELETE /api/orders/:id
```

### Products
```
GET /api/products
GET /api/products/:id
POST /api/products
PUT /api/products/:id
DELETE /api/products/:id
```

### Sales
```
GET /api/sales
GET /api/sales/reports/:period
```

### Customers
```
GET /api/customers
GET /api/customers/:id
POST /api/customers
PUT /api/customers/:id
```

## 📋 Environment Variables

Create `.env` file:

```
PORT=8000
NODE_ENV=development
MONGODB_URI=mongodb://localhost:27017/ecommerce-admin
JWT_SECRET=your_jwt_secret_key
JWT_EXPIRE=7d
CORS_ORIGIN=http://localhost:3000
```

## 🔗 Integration with Frontend

Frontend expects API at: `http://localhost:8000`

Update in `frontend/.env`:
```
VITE_API_BASE_URL=http://localhost:8000
```

## 🧪 Testing

```bash
# Start backend
npm run dev

# Frontend will connect to http://localhost:8000
cd ../frontend
npm run dev
```

## 📚 Sample Express Server

Create `server.js`:

```javascript
const express = require('express');
const cors = require('cors');
require('dotenv').config();

const app = express();
const PORT = process.env.PORT || 8000;

// Middleware
app.use(cors({
  origin: process.env.CORS_ORIGIN || 'http://localhost:3000'
}));
app.use(express.json());

// Mock auth endpoint for testing
app.post('/api/auth/login', (req, res) => {
  const { email, password } = req.body;
  
  if (email === 'admin@company.com' && password === 'admin123') {
    return res.json({
      token: 'your-jwt-token',
      employee: {
        id: 'EMP001',
        name: 'Admin User',
        email: email,
        role: 'Admin',
        department: 'Management'
      }
    });
  }
  
  res.status(401).json({ error: 'Invalid credentials' });
});

// Start server
app.listen(PORT, () => {
  console.log(`Server running on http://localhost:${PORT}`);
});
```

## 🔑 Authentication

Use JWT for token-based authentication:
- Generate token on login
- Send token in `Authorization: Bearer <token>` header
- Verify token on protected routes
- Refresh token on expiry

## 📦 Database

Recommended:
- MongoDB (NoSQL) - Good for flexible schemas
- PostgreSQL (SQL) - Good for relational data

## 🚀 Deployment

```bash
npm run build
npm start
```

## 📞 Next Steps

1. Initialize Node.js project
2. Install dependencies
3. Set up Express server
4. Configure database
5. Create authentication middleware
6. Implement API endpoints
7. Test with frontend
8. Deploy to production

---

See `../README.md` for full project information.
