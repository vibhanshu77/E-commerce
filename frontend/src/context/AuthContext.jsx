import React, { createContext, useState, useContext } from 'react'

const AuthContext = createContext()

export const AuthProvider = ({ children }) => {
  const [employee, setEmployee] = useState(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  const login = async (email, password) => {
    setLoading(true)
    setError(null)
    try {
      // Demo credentials for testing
      const DEMO_EMAIL = 'test@company.com'
      const DEMO_PASSWORD = 'password123'

      // Check if using demo credentials
      if (email === DEMO_EMAIL && password === DEMO_PASSWORD) {
        const mockEmployee = {
          id: 'EMP001',
          name: 'Test Employee',
          email: email,
          role: 'Sales Manager',
          department: 'Sales',
          joinDate: '2023-01-15',
          phone: '+1-234-567-8900',
        }
        
        const mockToken = 'demo-token-' + Date.now()
        localStorage.setItem('token', mockToken)
        localStorage.setItem('employee', JSON.stringify(mockEmployee))
        
        setEmployee(mockEmployee)
        setLoading(false)
        return true
      }

      // Try calling your backend API if not demo credentials
      const apiUrl = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8000'
      const response = await fetch(`${apiUrl}/api/auth/login`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password }),
      })

      if (!response.ok) {
        throw new Error('Invalid credentials')
      }

      const data = await response.json()
      
      // Store token in localStorage
      localStorage.setItem('token', data.token)
      localStorage.setItem('employee', JSON.stringify(data.employee))
      
      setEmployee(data.employee)
      setLoading(false)
      return true
    } catch (err) {
      setError(err.message || 'Login failed')
      setLoading(false)
      return false
    }
  }

  const logout = () => {
    localStorage.removeItem('token')
    localStorage.removeItem('employee')
    setEmployee(null)
    setError(null)
  }

  const loadEmployeeFromStorage = () => {
    const stored = localStorage.getItem('employee')
    if (stored) {
      setEmployee(JSON.parse(stored))
    }
  }

  return (
    <AuthContext.Provider value={{ employee, loading, error, login, logout, loadEmployeeFromStorage }}>
      {children}
    </AuthContext.Provider>
  )
}

export const useAuth = () => {
  const context = useContext(AuthContext)
  if (!context) {
    throw new Error('useAuth must be used within AuthProvider')
  }
  return context
}
