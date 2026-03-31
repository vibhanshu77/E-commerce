# Employee Dashboard - Project Setup Instructions

## Project Overview
A professional React + Vite employee dashboard for e-commerce management with Tailwind CSS styling. Features secure employee authentication, order management, product management, sales analytics, and customer management.

## Technology Stack
- React 18.2+
- Vite 5.0+
- Tailwind CSS 3.4+
- React Router v6
- JavaScript (ES6+)

## Setup Completed
- ✅ Project scaffolded with Vite
- ✅ Tailwind CSS configured
- ✅ React Router configured
- ✅ Authentication context set up
- ✅ Core pages created
- ✅ Responsive UI components built

## Project Structure
```
src/
├── components/         # Reusable components
├── pages/             # Page components
├── context/           # React Context
└── App.jsx           # Main application
```

## Key Features
1. **Employee Login** - Secure authentication system
2. **Dashboard** - Overview with key metrics
3. **Profile Management** - Employee profile updates
4. **Order Management** - View and filter orders
5. **Product Management** - Manage inventory
6. **Sales Reports** - Analytics and reporting
7. **Customer Management** - Customer information

## Development Workflow

### Start Development Server
```bash
npm run dev
```
Opens http://localhost:3000

### Build for Production
```bash
npm run build
```

### Preview Production Build
```bash
npm run preview
```

## API Integration

Replace the placeholder API calls in `src/context/AuthContext.jsx` with your backend endpoints:

**Authentication Endpoint:**
```javascript
POST /api/auth/login
Body: { email, password }
Response: { token, employee: { id, name, email, role, department } }
```

**Other Required Endpoints:**
- `GET /api/orders` - Fetch employee orders
- `GET /api/products` - Fetch e-commerce products
- `GET /api/sales` - Fetch sales data
- `GET /api/customers` - Fetch customer data

## Configuration Files

### Tailwind
- `tailwind.config.js` - Customize colors and extensions
- `postcss.config.js` - PostCSS plugins

### Vite
- `vite.config.js` - React plugin and dev server config

## Customization

### Update Colors
Edit `tailwind.config.js` colors configuration to match your branding.

### Add New Pages
1. Create page in `src/pages/`
2. Import in `App.jsx`
3. Add route
4. Add menu item in `Sidebar.jsx`

### Environment Variables
Create `.env` file:
```
VITE_API_BASE_URL=http://localhost:8000/api
```

## Security

- Tokens stored in localStorage
- Protected routes with `ProtectedRoute` component
- Login page accessible only when not authenticated
- Dashboard requires valid employee session

## Troubleshooting

**Port 3000 in use?** Change in `vite.config.js`
**Styles not loading?** Run `npm install` and restart dev server
**API errors?** Verify backend is running and endpoints are correct

## Next Steps

1. Update API endpoints in `AuthContext.jsx`
2. Customize colors in `tailwind.config.js`
3. Add company logo to sidebar
4. Implement actual API calls
5. Add error handling and validation
6. Deploy to production

## Testing

Demo credentials:
- Email: `test@company.com`
- Password: `password123`

Currently uses mock data. Replace with real API calls once backend is integrated.
