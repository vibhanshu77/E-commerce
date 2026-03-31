# Employee Dashboard - E-Commerce

A professional employee dashboard for e-commerce management built with React, Vite, and Tailwind CSS. Features employee authentication, order management, product management, sales reporting, and customer management.

## Features

- **Employee Authentication**: Secure login system with token-based authentication
- **Dashboard Overview**: Real-time statistics and key metrics
- **Employee Profile**: Manage personal profile information
- **Order Management**: View and filter orders by status
- **Product Management**: Add, edit, and manage product inventory
- **Sales Reports**: Detailed sales analytics and revenue tracking
- **Customer Management**: Customer information and purchase history
- **Responsive Design**: Mobile-friendly interface using Tailwind CSS

## Tech Stack

- **Frontend Framework**: React 18.2+
- **Build Tool**: Vite 5.0+
- **Styling**: Tailwind CSS 3.4+
- **Routing**: React Router v6
- **HTTP Client**: Axios
- **Package Manager**: npm

## Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd employee-dashboard
   ```

2. **Install dependencies**
   ```bash
   npm install
   ```

3. **Start the development server**
   ```bash
   npm run dev
   ```

   The application will open at `http://localhost:3000`

## Build

To build for production:

```bash
npm run build
```

The optimized build will be created in the `dist` folder.

## Project Structure

```
src/
├── components/
│   ├── Sidebar.jsx              # Navigation sidebar component
│   ├── DashboardLayout.jsx      # Dashboard layout wrapper
│   └── ProtectedRoute.jsx       # Route protection HOC
├── pages/
│   ├── Login.jsx                # Employee login page
│   ├── Dashboard.jsx            # Main dashboard
│   ├── Profile.jsx              # Employee profile
│   ├── Orders.jsx               # Order management
│   ├── Products.jsx             # Product management
│   ├── SalesReports.jsx         # Sales analytics
│   └── Customers.jsx            # Customer management
├── context/
│   └── AuthContext.jsx          # Authentication context
├── App.jsx                      # Main app component with routing
├── main.jsx                     # Entry point
└── index.css                    # Global styles with Tailwind
```

## Authentication

The dashboard uses a context-based authentication system:

- Login credentials are stored in `localStorage`
- Tokens are sent with API requests for authentication
- Protected routes redirect unauthenticated users to login

### Demo Login

For testing purposes:
- **Email**: `test@company.com`
- **Password**: `password123`

## API Integration

Update the API endpoints in `src/context/AuthContext.jsx`:

```javascript
const response = await fetch('/api/auth/login', {
  // Your API endpoint
})
```

Replace with your backend server endpoints:
- `POST /api/auth/login` - Employee login
- `GET /api/orders` - Fetch orders
- `GET /api/products` - Fetch products
- `GET /api/sales` - Fetch sales data
- `GET /api/customers` - Fetch customers

## Customization

### Colors
Modify the theme colors in `tailwind.config.js`:

```javascript
theme: {
  extend: {
    colors: {
      primary: '#1e3a8a',
      secondary: '#0f172a',
      accent: '#3b82f6',
    },
  },
}
```

### Menu Items
Add or remove menu items in `src/components/Sidebar.jsx` by updating the `menuItems` array.

## Development

### Adding a New Page

1. Create a new component in `src/pages/`
2. Import it in `src/App.jsx`
3. Add a route in the Routes section
4. Add a menu item in `src/components/Sidebar.jsx`

### Environment Variables

Create a `.env` file for API configuration:

```
VITE_API_BASE_URL=http://localhost:8000/api
VITE_APP_NAME=Employee Dashboard
```

Access in code:
```javascript
const apiUrl = import.meta.env.VITE_API_BASE_URL
```

## Performance

- Vite provides fast HMR (Hot Module Replacement) during development
- Optimized production build with code splitting
- Tailwind CSS purges unused styles in production

## Browser Support

- Chrome (latest)
- Firefox (latest)
- Safari (latest)
- Edge (latest)

## Troubleshooting

### Port 3000 Already in Use
Modify `vite.config.js`:
```javascript
server: {
  port: 3001,
}
```

### Login Not Working
1. Ensure your backend API is running
2. Check API endpoint URLs in `AuthContext.jsx`
3. Verify CORS settings are configured

### Styles Not Loading
1. Run `npm install` again
2. Delete `node_modules` and reinstall
3. Restart the dev server

## License

MIT License - feel free to use this project for your e-commerce platform.

## Support

For issues, questions, or suggestions, please open an issue in the repository.
