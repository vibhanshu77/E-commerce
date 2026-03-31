import React, { useState, useEffect } from 'react'

const SalesReports = () => {
  const [period, setPeriod] = useState('month')
  const [salesData, setSalesData] = useState([])

  useEffect(() => {
    // Fetch sales data from API
    // Mock data for demo
    setSalesData([
      { date: '2024-03-01', sales: 12500, orders: 45, revenue: 12500 },
      { date: '2024-03-05', sales: 15230, orders: 52, revenue: 15230 },
      { date: '2024-03-10', sales: 18900, orders: 61, revenue: 18900 },
      { date: '2024-03-15', sales: 22100, orders: 78, revenue: 22100 },
      { date: '2024-03-20', sales: 19800, orders: 68, revenue: 19800 },
      { date: '2024-03-25', sales: 25600, orders: 89, revenue: 25600 },
      { date: '2024-03-31', sales: 28300, orders: 95, revenue: 28300 },
    ])
  }, [period])

  const totalRevenue = salesData.reduce((sum, item) => sum + item.revenue, 0)
  const totalOrders = salesData.reduce((sum, item) => sum + item.orders, 0)
  const avgDailySales = (totalRevenue / salesData.length).toFixed(2)

  return (
    <div className="flex-1 p-8">
      <h1 className="text-4xl font-bold text-gray-800 mb-8">Sales Reports</h1>

      {/* Period Selector */}
      <div className="flex gap-4 mb-8">
        {['week', 'month', 'quarter', 'year'].map(p => (
          <button
            key={p}
            onClick={() => setPeriod(p)}
            className={`px-4 py-2 rounded-lg font-semibold transition capitalize ${
              period === p
                ? 'bg-accent text-white'
                : 'bg-gray-200 text-gray-700 hover:bg-gray-300'
            }`}
          >
            {p}
          </button>
        ))}
      </div>

      {/* Summary Cards */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
        <div className="bg-gradient-to-br from-blue-500 to-blue-600 rounded-lg shadow p-6 text-white">
          <p className="text-blue-100">Total Revenue</p>
          <p className="text-4xl font-bold mt-2">${totalRevenue.toLocaleString()}</p>
        </div>
        <div className="bg-gradient-to-br from-green-500 to-green-600 rounded-lg shadow p-6 text-white">
          <p className="text-green-100">Total Orders</p>
          <p className="text-4xl font-bold mt-2">{totalOrders}</p>
        </div>
        <div className="bg-gradient-to-br from-purple-500 to-purple-600 rounded-lg shadow p-6 text-white">
          <p className="text-purple-100">Average Daily Sales</p>
          <p className="text-4xl font-bold mt-2">${avgDailySales}</p>
        </div>
      </div>

      {/* Sales Data Table */}
      <div className="bg-white rounded-lg shadow p-6">
        <h2 className="text-2xl font-bold text-gray-800 mb-4">Daily Sales Overview</h2>
        <div className="overflow-x-auto">
          <table className="w-full">
            <thead>
              <tr className="border-b border-gray-200">
                <th className="text-left py-3 px-4 font-semibold text-gray-700">Date</th>
                <th className="text-left py-3 px-4 font-semibold text-gray-700">Orders</th>
                <th className="text-left py-3 px-4 font-semibold text-gray-700">Sales</th>
                <th className="text-left py-3 px-4 font-semibold text-gray-700">Revenue</th>
                <th className="text-left py-3 px-4 font-semibold text-gray-700">Growth</th>
              </tr>
            </thead>
            <tbody>
              {salesData.map((item, index) => {
                const prevRevenue = index > 0 ? salesData[index - 1].revenue : item.revenue
                const growth = ((item.revenue - prevRevenue) / prevRevenue * 100).toFixed(1)
                return (
                  <tr key={item.date} className="border-b border-gray-100 hover:bg-gray-50">
                    <td className="py-3 px-4 font-semibold text-gray-800">{item.date}</td>
                    <td className="py-3 px-4 text-gray-600">{item.orders}</td>
                    <td className="py-3 px-4 text-gray-600">${item.sales}</td>
                    <td className="py-3 px-4 font-semibold text-accent">${item.revenue}</td>
                    <td className={`py-3 px-4 font-semibold ${growth > 0 ? 'text-green-600' : 'text-red-600'}`}>
                      {growth > 0 ? '↑' : '↓'} {Math.abs(growth)}%
                    </td>
                  </tr>
                )
              })}
            </tbody>
          </table>
        </div>
      </div>

      {/* Export Options */}
      <div className="mt-8 flex gap-4">
        <button className="bg-accent hover:bg-blue-700 text-white font-bold py-2 px-6 rounded-lg transition">
          📥 Export to CSV
        </button>
        <button className="bg-purple-600 hover:bg-purple-700 text-white font-bold py-2 px-6 rounded-lg transition">
          🖨️ Print Report
        </button>
      </div>
    </div>
  )
}

export default SalesReports
