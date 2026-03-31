import React, { useEffect } from 'react'
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom'
import { AuthProvider, useAuth } from './context/AuthContext'
import ProtectedRoute from './components/ProtectedRoute'
import DashboardLayout from './components/DashboardLayout'
import Login from './pages/Login'
import Dashboard from './pages/Dashboard'
import Profile from './pages/Profile'
import Orders from './pages/Orders'
import Products from './pages/Products'
import SalesReports from './pages/SalesReports'
import Customers from './pages/Customers'

function AppContent() {
  const { employee, loadEmployeeFromStorage } = useAuth()

  useEffect(() => {
    loadEmployeeFromStorage()
  }, [])

  return (
    <Routes>
      <Route path="/" element={employee ? <Navigate to="/dashboard" replace /> : <Login />} />
      
      <Route
        path="/*"
        element={
          <ProtectedRoute>
            <DashboardLayout />
          </ProtectedRoute>
        }
      >
        <Route path="dashboard" element={<Dashboard />} />
        <Route path="profile" element={<Profile />} />
        <Route path="orders" element={<Orders />} />
        <Route path="products" element={<Products />} />
        <Route path="sales" element={<SalesReports />} />
        <Route path="customers" element={<Customers />} />
      </Route>
    </Routes>
  )
}

function App() {
  return (
    <Router>
      <AuthProvider>
        <AppContent />
      </AuthProvider>
    </Router>
  )
}

export default App
