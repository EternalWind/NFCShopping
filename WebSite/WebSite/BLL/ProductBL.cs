﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.DAL;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.BLL
{
    public class ProductBL : IDisposable
    {
        private bool mIsDisposed = false;

        private IProductRepository mProductRepository;

        public ProductBL()
        {
            mProductRepository = new ProductRepository();
        }

        public ProductBL(IProductRepository productRepository)
        {
            mProductRepository = productRepository;
        }

        public IEnumerable<Product> GetProducts()
        {
            return mProductRepository.GetProducts();
        }

        public IEnumerable<Product> GetProductsByCategory(Int32 categoryID)
        {
            return GetProducts().Where(product => product.SecCategory.categoryID == categoryID);
        }

        public IEnumerable<Product> GetProductsBySecCategory(Int32 secCategoryID)
        {
            return GetProducts().Where(product => product.secCategoryID == secCategoryID);
        }

        public Product GetProductByBarcode(String barcode)
        {
            return GetProducts().ToList().Find(p=>p.barCode.Equals(barcode));
        }

        public IEnumerable<Product> GetProductsByName(String name)
        {
            return GetProducts().Where(product => product.productName == name);
        }

        public Product GetProduct(Int32 id)
        {
            try
            {
                return GetProducts().Single(product => product.productID == id);
            }
            catch (Exception ex)
            {
                // TODO: Add exception handling code here.

                return null;
            }

        }

        public void InsertProduct(Product product)
        {
            try
            {
                mProductRepository.InsertProduct(product, true);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        public void DeleteProduct(Product product)
        {
            try
            {
                mProductRepository.DeleteProduct(product, true);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        public void UpdateProduct(Product product, Product origProduct)
        {
            try
            {
                mProductRepository.UpdateProduct(product, origProduct, true);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        protected void Dispose(bool isDisposing)
        {
            if (!mIsDisposed)
            {
                if (isDisposing)
                {
                    mProductRepository.Dispose();
                }

                mIsDisposed = true;
            }
        }

        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }
    }
}