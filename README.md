## Topik

- Online Clothing Store Application

## Table :

- users
    - id (UUIDv4)
    - full_name (VARCHAR)
    - address (VARCHAR)
    - email (VARCHAR)
    - phone_no (VARCHAR)
    - password (VARCHAR)
    - role (VARCHAR)
- purchase_transactions
    - id (UUIDv4)
    - user_id  (VARCHAR)
    - transaction_date (TIMESTAMP)
    - transaction_code (VARCHAR)
- purchase_transaction_details
    - id (UUIDv4)
    - purchase_transaction_id  (VARCHAR) (FK)
    - supplier_item_id  (VARCHAR) (FK)
    - qty (INT)
    - total_price (DECIMAL)
- in_stock_items
    - id (UUIDv4)
    - supplier_item_id  (VARCHAR)(FK)
    - qty (INT)
- warehouse_items
    - id (UUIDv4)
    - supplier_item_id  (VARCHAR)(FK)
    - qty (INT)
- supplier_items
    - id (UUIDv4)
    - user_id(VARCHAR)(FK)
    - name  (VARCHAR)
    - price (DECIMAL)
- item_requests
    - id (UUIDv4)
    - user_supplier_id  (VARCHAR)(FK)
    - acceptance_status  (VARCHAR)
    - transaction_date (TIMESTAMP)
    - transaction_code  (VARCHAR)
- Item_request_details
    - id (UUIDv4)
    - item_request_id  (VARCHAR)(FK)
    - supplier_item_id  (VARCHAR)(FK)
    - qty (INT)

## Flow :

- User - Customer - Pembelian barang
    - Register
    - Login
    - List barang
        - Search item
        - Milih barang & Jumlah barang
    - Beli barang
        - Terima bukti pembelian

- User - Admin - Pengaturan stok barang
    - Login
    - List barang
    - Tambah Stok barang dari gudang
    - Request barang ke supplier
        - Memilih supplier / Search item
        - Memilih jenis barang dan jumlah barang
    - Register supplier

- User - Supplier - Stock barang
    - Login
    - Melihat list barangnya
    - Nambah list barangnya
    - Confirmasi supply demand
