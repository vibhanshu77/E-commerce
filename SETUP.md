# Setup Instructions - E-Commerce Admin Dashboard

Complete setup guide for the full-stack e-commerce admin dashboard project.

## 📋 Prerequisites

- Node.js (v14+)
- npm or yarn
- Git
- Optional: MongoDB or PostgreSQL for database

## 🚀 Project Setup

### 1. Navigate to Frontend

```bash
cd frontend
```

### 2. Install Dependencies

```bash
npm install
```

### 3. Start Development Server

```bash
npm run dev
```

The frontend will open at `http://localhost:3000`

### 4. Login with Demo Credentials

```
Email:    test@company.com
Password: password123
```

## 🔄 Development Workflow

### Frontend Development

```bash
cd frontend

# Start dev server (with hot reload)
npm run dev

# Build for production
npm run build

# Preview production build
npm run preview
```

### Backend Development (To Be Created)

```bash
cd backend

# Initialize the project (first time)
npm init -y
npm install express cors dotenv mongoose jsonwebtoken bcryptjs

# Start development server
npm run dev
```

## 📁 Project Structure

```
project-root/
│
├── frontend/                # React Vite application ✅ Ready
│   ├── src/
│   ├── package.json
│   ├── vite.config.js
│   └── README.md
│
├── backend/                 # Node.js Express API (To Be Created)
│   ├── routes/
│   ├── controllers/
│   ├── models/
│   └── README.md (Setup instructions)
│
└── README.md               # Root documentation
```

## 🔧 Configuration

### Frontend Environment

Create `frontend/.env`:

```
VITE_API_BASE_URL=http://localhost:8000
VITE_APP_NAME=Employee Dashboard
```

### Backend Environment

Create `backend/.env`:

```
PORT=8000
NODE_ENV=development
MONGODB_URI=mongodb://localhost:27017/ecommerce
JWT_SECRET=your_secret_key
CORS_ORIGIN=http://localhost:3000
```

## 🧪 Testing the Full Stack

### Step 1: Start Frontend

```bash
cd frontend
npm run dev
```

Opens at `http://localhost:3000`

### Step 2: Start Backend (when ready)

```bash
cd backend
npm run dev
```

Runs at `http://localhost:8000`

### Step 3: Login and Test

- Enter demo credentials
- Check browser DevTools for API calls
- Verify responses from backend

## 📚 Documentation

- **Frontend** → `frontend/README.md`
- **Backend Setup** → `backend/README.md`
- **Quick Start** → `frontend/QUICKSTART.md`
- **Root** → `README.md`

## 🔗 API Integration Points

Frontend currently has demo authentication. To connect backend:

1. Update `VITE_API_BASE_URL` in `frontend/.env`
2. Create backend API endpoints (see `backend/README.md`)
3. Test login with backend credentials
4. Verify all features work end-to-end

## 🛠️ Common Commands

### Frontend

```bash
cd frontend

npm run dev              # Start dev server
npm run build           # Build for production
npm run preview         # Preview production build
npm run lint           # Lint code
npm install            # Install dependencies
npm install <package>  # Add new package
```

### Backend (Example)

```bash
cd backend

npm run dev            # Start dev server
npm start             # Start server
npm install           # Install dependencies
npm install <package> # Add new package
```

## 🚨 Troubleshooting

### Port 3000 Already in Use?

```bash
cd frontend
npm run dev -- --port 3001
```

### Dependencies Not Installed?

```bash
cd frontend
rm -rf node_modules package-lock.json
npm install
```

### Frontend Not Connecting to Backend?

- Check `VITE_API_BASE_URL` in `frontend/.env`
- Verify backend server is running on port 8000
- Check CORS configuration in backend
- Check browser console for error messages

### Build Fails?

```bash
cd frontend
npm run build

# Check for errors in output
```

## 📦 Project Features

### ✅ Frontend (Ready)

- React + Vite setup
- Tailwind CSS styling
- React Router navigation
- Authentication context
- 7 functional pages:
  - Login
  - Dashboard
  - Profile
  - Orders
  - Products
  - Sales Reports
  - Customers
- Responsive design
- Demo authentication

### ⏳ Backend (To Create)

- Express.js API
- JWT authentication
- Database models
- API endpoints
- CORS configuration
- Error handling

## 🚀 Deployment

### Frontend

```bash
cd frontend
npm run build

# Deploy dist/ folder to:
# - Vercel
# - Netlify
# - AWS S3
# - Any static host
```

### Backend

Deploy to:
- Heroku
- AWS EC2
- DigitalOcean
- Railway
- Render

## 📞 Need Help?

1. Check relevant README files
2. Review QUICKSTART.md
3. Check browser console for errors
4. Verify environment variables
5. Check both frontend and backend are running

## ✨ Next Steps

1. **Now** → Frontend is ready, test with demo credentials
2. **Next** → Create backend API (see `backend/README.md`)
3. **Then** → Connect frontend to backend
4. **Finally** → Deploy to production

---

**Happy coding!** 🎉

For detailed information, see the README.md files in each folder.
