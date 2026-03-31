import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'

const Dashboard = () => {
  const { employee } = useAuth()
  const [stats, setStats] = useState({
    totalOrders: 0,
    totalSales: 0,
    newCustomers: 0,
    activeProducts: 0,
  })

  useEffect(() => {
    // Fetch dashboard stats from your API
    // For demo purposes, using mock data
    setStats({
      totalOrders: 1240,
      totalSales: 45230,
      newCustomers: 89,
      activeProducts: 342,
    })
  }, [])

  const StatCard = ({ title, value, icon, color }) => (
    <div className="bg-white rounded-lg shadow p-6">
      <div className="flex items-center justify-between">
        <div>
          <p className="text-gray-600 text-sm font-medium">{title}</p>
          <p className={`text-3xl font-bold mt-2 ${color}`}>{value}</p>
        </div>
        <div className="text-4xl">{icon}</div>
      </div>
    </div>
  )

  return (
    <div className="flex-1 p-8">
      <div className="mb-8">
        <h1 className="text-4xl font-bold text-gray-800">Welcome back, {employee?.name || 'Employee'}!</h1>
        <p className="text-gray-600 text-lg mt-2">Here's your dashboard overview</p>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
        <StatCard
          title="Total Orders"
          value={stats.totalOrders}
          icon="📦"
          color="text-blue-600"
        />
        <StatCard
          title="Total Sales"
          value={`$${stats.totalSales}`}
          icon="💰"
          color="text-green-600"
        />
        <StatCard
          title="New Customers"
          value={stats.newCustomers}
          icon="👥"
          color="text-purple-600"
        />
        <StatCard
          title="Active Products"
          value={stats.activeProducts}
          icon="🛍️"
          color="text-orange-600"
        />
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        <div className="lg:col-span-2 bg-white rounded-lg shadow p-6">
          <h2 className="text-xl font-bold text-gray-800 mb-4">Recent Activity</h2>
          <div className="space-y-4">
            <div className="flex items-center justify-between border-b pb-4">
              <div>
                <p className="font-semibold text-gray-700">Order #12345</p>
                <p className="text-sm text-gray-500">Customer: John Doe</p>
              </div>
              <span className="bg-green-100 text-green-800 px-3 py-1 rounded-full text-sm">Completed</span>
            </div>
            <div className="flex items-center justify-between border-b pb-4">
              <div>
                <p className="font-semibold text-gray-700">Order #12344</p>
                <p className="text-sm text-gray-500">Customer: Jane Smith</p>
              </div>
              <span className="bg-yellow-100 text-yellow-800 px-3 py-1 rounded-full text-sm">Pending</span>
            </div>
            <div className="flex items-center justify-between">
              <div>
                <p className="font-semibold text-gray-700">Order #12343</p>
                <p className="text-sm text-gray-500">Customer: Bob Johnson</p>
              </div>
              <span className="bg-blue-100 text-blue-800 px-3 py-1 rounded-full text-sm">Processing</span>
            </div>
          </div>
        </div>

        <div className="bg-white rounded-lg shadow p-6">
          <h2 className="text-xl font-bold text-gray-800 mb-4">Quick Links</h2>
          <div className="space-y-3">
            <Link to="/orders" className="block p-3 bg-blue-50 hover:bg-blue-100 rounded-lg text-blue-700 font-semibold transition">
              View All Orders
            </Link>
            <Link to="/products" className="block p-3 bg-purple-50 hover:bg-purple-100 rounded-lg text-purple-700 font-semibold transition">
              Manage Products
            </Link>
            <Link to="/sales" className="block p-3 bg-green-50 hover:bg-green-100 rounded-lg text-green-700 font-semibold transition">
              Sales Report
            </Link>
            <Link to="/customers" className="block p-3 bg-orange-50 hover:bg-orange-100 rounded-lg text-orange-700 font-semibold transition">
              Customer Base
            </Link>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Dashboard
