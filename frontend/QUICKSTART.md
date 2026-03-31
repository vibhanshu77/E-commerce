# 🚀 Quick Start Guide - Employee Dashboard

## Getting Started (30 seconds)

### 1. Start Development Server
```bash
npm run dev
```
The app will automatically open at `http://localhost:3000`

### 2. Login with Demo Account
- **Email**: `test@company.com`
- **Password**: `password123`

### 3. Explore the Dashboard
- 📊 **Dashboard** - View key metrics and recent activity
- 👤 **Profile** - Manage your employee profile
- 📦 **Orders** - View and filter customer orders
- 🛍️ **Products** - Manage product inventory
- 📈 **Sales Reports** - Track sales and revenue
- 👥 **Customers** - Manage customer information

## Available Scripts

```bash
# Start development server (port 3000)
npm run dev

# Build for production
npm run build

# Preview production build
npm run preview

# Lint code
npm run lint
```

## 🔧 Configuration

### API Endpoints
Update your API base URL in `.env`:
```
VITE_API_BASE_URL=http://your-backend-api.com
```

### Theme Colors
Customize theme in `tailwind.config.js`:
```javascript
colors: {
  primary: '#1e3a8a',      // Sidebar background
  secondary: '#0f172a',    // Secondary color
  accent: '#3b82f6',       // Button & link color
}
```

## 📁 Project Structure

```
src/
├── pages/          # Main page components
├── components/     # Reusable UI components
├── context/        # Authentication context
└── App.jsx        # Main app with routing
```

## 🔐 Authentication Flow

1. User enters credentials on login page
2. POST request sent to `/api/auth/login`
3. Backend returns token and employee data
4. Token stored in localStorage
5. User redirected to dashboard
6. Protected routes check for valid token

## 🎨 Adding Features

### Add New Page
1. Create file in `src/pages/`
2. Add route in `App.jsx`
3. Add menu item in `Sidebar.jsx`

Example:
```jsx
// src/pages/Analytics.jsx
export default function Analytics() {
  return <div>Analytics Page</div>
}

// Then add to App.jsx routes:
<Route path="analytics" element={<Analytics />} />

// And to Sidebar.jsx menuItems:
{ path: '/analytics', icon: '📊', label: 'Analytics' }
```

## 📚 Key Components

### AuthContext
Manages user authentication state and login/logout logic.

### ProtectedRoute
Prevents unauthorized access to dashboard pages.

### DashboardLayout
Combines sidebar navigation with page content.

## 🐛 Troubleshooting

**Port 3000 in use?**
```bash
npm run dev -- --port 3001
```

**Dependencies not working?**
```bash
rm -rf node_modules
npm install
```

**Build fails?**
```bash
npm run build
```

## 📖 More Information

See [README.md](./README.md) for detailed documentation.

## 💡 Tips

- Use browser DevTools to inspect network requests
- Check localStorage for token and user data
- Customize demo data in page components
- Add error handling for API calls
- Implement real API integration when backend is ready

---

**Ready to develop?** Run `npm run dev` and start building! 🎉
