import React from 'react'
import { Link, useLocation } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'

const Sidebar = () => {
  const location = useLocation()
  const { logout } = useAuth()

  const menuItems = [
    { path: '/dashboard', icon: '📊', label: 'Dashboard' },
    { path: '/profile', icon: '👤', label: 'Profile' },
    { path: '/orders', icon: '📦', label: 'Orders' },
    { path: '/products', icon: '🛍️', label: 'Products' },
    { path: '/sales', icon: '📈', label: 'Sales Reports' },
    { path: '/customers', icon: '👥', label: 'Customers' },
  ]

  const isActive = (path) => location.pathname === path

  return (
    <div className="w-64 bg-primary text-white min-h-screen flex flex-col shadow-lg">
      <div className="p-6 border-b border-blue-700">
        <h1 className="text-2xl font-bold">E-Commerce</h1>
        <p className="text-blue-200 text-sm">Employee Dashboard</p>
      </div>

      <nav className="flex-1 p-4">
        <ul className="space-y-2">
          {menuItems.map((item) => (
            <li key={item.path}>
              <Link
                to={item.path}
                className={`flex items-center px-4 py-3 rounded-lg transition ${
                  isActive(item.path)
                    ? 'bg-accent text-white'
                    : 'text-blue-100 hover:bg-blue-600'
                }`}
              >
                <span className="mr-3 text-xl">{item.icon}</span>
                <span className="font-medium">{item.label}</span>
              </Link>
            </li>
          ))}
        </ul>
      </nav>

      <div className="p-4 border-t border-blue-700">
        <button
          onClick={logout}
          className="w-full bg-red-600 hover:bg-red-700 text-white font-semibold py-2 px-4 rounded-lg transition"
        >
          🚪 Logout
        </button>
      </div>
    </div>
  )
}

export default Sidebar
