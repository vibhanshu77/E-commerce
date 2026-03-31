# E-Commerce Admin Dashboard - Full Stack Project

A complete employee dashboard solution for e-commerce management built with React and Vite.

## 📁 Project Structure

```
project-root/
├── frontend/                    # React + Vite frontend application
│   ├── src/                    # Source code
│   │   ├── components/         # Reusable components
│   │   ├── pages/             # Page components
│   │   ├── context/           # React Context (Auth)
│   │   ├── App.jsx            # Main app component
│   │   ├── main.jsx           # Entry point
│   │   └── index.css          # Global styles
│   ├── dist/                  # Production build output
│   ├── package.json           # NPM dependencies
│   ├── vite.config.js         # Vite configuration
│   ├── tailwind.config.js     # Tailwind CSS config
│   ├── postcss.config.js      # PostCSS config
│   ├── index.html             # HTML template
│   ├── README.md              # Frontend documentation
│   ├── QUICKSTART.md          # Quick start guide
│   └── SETUP_COMPLETE.md      # Setup information
│
├── backend/                    # Backend API (to be created)
│   ├── routes/                # API endpoints
│   ├── controllers/           # Request handlers
│   ├── models/               # Database models
│   └── ...
│
└── README.md                  # This file
```

## 🚀 Quick Start

### Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

The frontend will start at `http://localhost:3000`

### Demo Credentials
```
Email:    test@company.com
Password: password123
```

## 🔧 Technologies

### Frontend
- React 18.2+
- Vite 5.0+
- Tailwind CSS 3.4+
- React Router v6
- Axios

### Backend (To be created)
- Node.js / Express
- MongoDB / PostgreSQL
- JWT Authentication
- REST API

## 📊 Frontend Features

✅ **Employee Authentication**
- Secure login with JWT tokens
- Demo authentication available
- Protected routes

✅ **Dashboard Pages**
- Dashboard overview with metrics
- Employee profile management
- Order management and filtering
- Product management
- Sales reports and analytics
- Customer management

✅ **Responsive UI**
- Mobile-friendly design
- Tailwind CSS styling
- Color-coded status indicators
- Quick action links

## 🔐 Authentication Flow

1. Employee logs in with email/password
2. Backend validates credentials and returns JWT token
3. Token stored in localStorage
4. All API requests include token in Authorization header
5. Protected routes verify authentication

## 🔌 API Integration

### Frontend expects these endpoints:

```
POST /api/auth/login
- Body: { email, password }
- Response: { token, employee: { id, name, email, role, department } }

GET /api/orders
GET /api/products
GET /api/sales
GET /api/customers
```

### Setup Backend API URL

In `frontend/.env`:
```
VITE_API_BASE_URL=http://localhost:8000
```

## 📝 Configuration

### Frontend Environment Variables

Create `frontend/.env`:
```
VITE_API_BASE_URL=http://localhost:8000
VITE_APP_NAME=Employee Dashboard
```

### Frontend NPM Scripts

```bash
npm run dev       # Development server
npm run build     # Production build
npm run preview   # Preview production build
npm run lint      # Code linting
```

## 🛠️ Development Workflow

1. **Start Frontend**
   ```bash
   cd frontend
   npm run dev
   ```

2. **Start Backend** (when created)
   ```bash
   cd backend
   npm run dev
   ```

3. **Test Integration**
   - Login with demo credentials
   - Test API calls
   - Verify data flows

## 🔄 Next Steps

1. ✅ Frontend dashboard created
2. 📋 Create backend API server
3. 🔗 Connect frontend to backend
4. 🗄️ Set up database
5. 🧪 Write tests
6. 📦 Deploy to production

## 📚 Documentation

- **Frontend** - See `frontend/README.md` for detailed frontend documentation
- **Quick Start** - See `frontend/QUICKSTART.md` for quick reference
- **Setup Info** - See `frontend/SETUP_COMPLETE.md` for setup details

## 🐛 Troubleshooting

### Frontend not connecting to backend?
- Verify `VITE_API_BASE_URL` in `frontend/.env`
- Check backend server is running
- Check CORS is configured on backend

### Port already in use?
```bash
cd frontend
npm run dev -- --port 3001
```

### Dependencies issues?
```bash
cd frontend
rm -rf node_modules package-lock.json
npm install
```

## 📞 Support

For issues or questions:
1. Check the README files in frontend/
2. Review QUICKSTART.md for common issues
3. Check backend API endpoints
4. Verify environment variables

## 📄 License

MIT License - Available for e-commerce platforms

---

**Status**: Frontend ✅ Ready | Backend ⏳ To be created

Start building! 🚀
