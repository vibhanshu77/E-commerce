import React, { useState, useEffect } from 'react'

const Products = () => {
  const [products, setProducts] = useState([])
  const [selectedCategory, setSelectedCategory] = useState('all')
  const [showAddNew, setShowAddNew] = useState(false)

  useEffect(() => {
    // Fetch products from API
    // Mock data for demo
    setProducts([
      { id: 1, name: 'Laptop Pro', category: 'electronics', price: 1299, stock: 25, status: 'active' },
      { id: 2, name: 'Wireless Mouse', category: 'electronics', price: 49, stock: 150, status: 'active' },
      { id: 3, name: 'USB-C Cable', category: 'accessories', price: 15, stock: 500, status: 'active' },
      { id: 4, name: 'Monitor 27"', category: 'electronics', price: 399, stock: 10, status: 'active' },
      { id: 5, name: 'Keyboard Mechanical', category: 'accessories', price: 129, stock: 45, status: 'active' },
    ])
  }, [])

  const categories = ['all', 'electronics', 'accessories', 'clothing']

  const filteredProducts = selectedCategory === 'all'
    ? products
    : products.filter(p => p.category === selectedCategory)

  const handleDeleteProduct = (id) => {
    if (confirm('Are you sure you want to delete this product?')) {
      setProducts(products.filter(p => p.id !== id))
    }
  }

  return (
    <div className="flex-1 p-8">
      <div className="flex justify-between items-center mb-8">
        <h1 className="text-4xl font-bold text-gray-800">Products</h1>
        <button
          onClick={() => setShowAddNew(!showAddNew)}
          className="bg-accent hover:bg-blue-700 text-white font-bold py-2 px-6 rounded-lg transition"
        >
          {showAddNew ? '✕ Close' : '+ Add New Product'}
        </button>
      </div>

      {/* Add New Product Form */}
      {showAddNew && (
        <div className="bg-white rounded-lg shadow p-6 mb-8">
          <h2 className="text-2xl font-bold text-gray-800 mb-4">Add New Product</h2>
          <form className="grid grid-cols-1 md:grid-cols-2 gap-4">
            <input type="text" placeholder="Product Name" className="px-4 py-2 border border-gray-300 rounded-lg" />
            <select className="px-4 py-2 border border-gray-300 rounded-lg">
              <option>Select Category</option>
              <option>Electronics</option>
              <option>Accessories</option>
              <option>Clothing</option>
            </select>
            <input type="number" placeholder="Price" className="px-4 py-2 border border-gray-300 rounded-lg" />
            <input type="number" placeholder="Stock Quantity" className="px-4 py-2 border border-gray-300 rounded-lg" />
            <textarea placeholder="Description" className="col-span-full px-4 py-2 border border-gray-300 rounded-lg"></textarea>
            <button type="submit" className="col-span-full bg-green-600 hover:bg-green-700 text-white font-bold py-2 px-4 rounded-lg transition">
              Add Product
            </button>
          </form>
        </div>
      )}

      {/* Category Filter */}
      <div className="flex gap-2 mb-6 overflow-x-auto pb-2">
        {categories.map(cat => (
          <button
            key={cat}
            onClick={() => setSelectedCategory(cat)}
            className={`px-4 py-2 rounded-lg font-semibold transition whitespace-nowrap ${
              selectedCategory === cat
                ? 'bg-accent text-white'
                : 'bg-gray-200 text-gray-700 hover:bg-gray-300'
            }`}
          >
            {cat.charAt(0).toUpperCase() + cat.slice(1)}
          </button>
        ))}
      </div>

      {/* Products Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {filteredProducts.map(product => (
          <div key={product.id} className="bg-white rounded-lg shadow hover:shadow-lg transition overflow-hidden">
            <div className="h-40 bg-gradient-to-br from-gray-200 to-gray-300 flex items-center justify-center">
              <span className="text-5xl">📦</span>
            </div>
            <div className="p-4">
              <h3 className="text-lg font-bold text-gray-800 mb-2">{product.name}</h3>
              <div className="flex justify-between items-center mb-3">
                <span className="text-2xl font-bold text-accent">${product.price}</span>
                <span className={`px-3 py-1 rounded-full text-sm font-semibold ${
                  product.stock > 0 ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'
                }`}>
                  {product.stock} in stock
                </span>
              </div>
              <div className="flex gap-2 text-sm">
                <button className="flex-1 bg-accent hover:bg-blue-700 text-white font-semibold py-2 px-3 rounded transition">
                  Edit
                </button>
                <button
                  onClick={() => handleDeleteProduct(product.id)}
                  className="flex-1 bg-red-600 hover:bg-red-700 text-white font-semibold py-2 px-3 rounded transition"
                >
                  Delete
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>

      {filteredProducts.length === 0 && (
        <div className="text-center py-12 text-gray-500">
          No products found in this category
        </div>
      )}
    </div>
  )
}

export default Products
