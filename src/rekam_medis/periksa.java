package rekam_medis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author acer
 */

public class periksa extends javax.swing.JFrame {
private Connection con;
private Statement st;
private ResultSet RsPasien;
private ResultSet RsDokter;
private ResultSet RsPeriksa;
private ResultSet RsDetail;

private ResultSet RsObat;
private String sql="";

private String no, kodepas, namapas, alamat, nohp, kodedok, kodeobat, namadok, keahlian, penyakit;
private int hargaobat, jumlahobat,  jumlahitem, jumlahtotal, bayar, kembali;
  
    public periksa() {
        initComponents();
        koneksitabel();
        DaftarDoktor();
        DaftarPasien();
        DaftarObat();
        this.setLocationRelativeTo(null);
        tampildata("SELECT * FROM tabel_periksa");
        tampildetailpemeriksaan("select * from tabel_detail_periksa");
    }
    
    private void form_disable(){
        nama_pasien.setEnabled(false);
        alamat_pasien.setEnabled(false);
        no_hp_pasien.setEnabled(false);
        keahlian_dokter.setEnabled(false);
        penyakit_pasien.setEnabled(false);
        nama_dokter.setEnabled(false);
        nama_obat.setEnabled(false);
        jenis_obat.setEnabled(false);
        harga_obat.setEnabled(false);
        total_harga.setEnabled(false);
        kode_dokter.setEnabled(false);
        kode_pasien.setEnabled(false);
        kode_obat.setEnabled(false);
        tanggal_periksa.setEnabled(false);
    }
    
    private void form_enable(){
        kode_periksa.setEnabled(true);
        total_harga.setEnabled(true);
        jumlah_obat.setEnabled(true);
        nama_pasien.setEnabled(true);
        alamat_pasien.setEnabled(true);
        no_hp_pasien.setEnabled(true);
        keahlian_dokter.setEnabled(true);
        penyakit_pasien.setEnabled(true);
        nama_dokter.setEnabled(true);
        nama_obat.setEnabled(true);
        jenis_obat.setEnabled(true);
        harga_obat.setEnabled(true);
        kode_dokter.setEnabled(true);
        kode_pasien.setEnabled(true);
        kode_obat.setEnabled(true);
        tanggal_periksa.setEnabled(true);
    }
    
    private void form_clear(){
        kode_periksa.setText("");
        nama_pasien.setText("");
        alamat_pasien.setText("");
        no_hp_pasien.setText("");
        keahlian_dokter.setText("");
        penyakit_pasien.setText("");
        tanggal_periksa.setDate(null);
        nama_dokter.setText("");
        total_harga.setText("");
        nama_obat.setText("");
        harga_obat.setText("");
        jenis_obat.setText("");
        jumlah_bayar.setText("");
        jumlah_item.setText("");
        jumlah_obat.setText("");
        jumlah_total.setText("");
        kembalian.setText("");
        kode_dokter.setSelectedItem("Pilih");
        kode_pasien.setSelectedItem("Pilih");
        kode_obat.setSelectedItem("Pilih");
    }
    
    private void aksi_tambah(){
        form_enable();
        Btn_Tambah.setEnabled(true);    
        Btn_Kurang.setEnabled(true);
        btn_simpan.setEnabled(true);
        btn_batal.setEnabled(true);
        kode_periksa.requestFocus(true);
    }
    
    private void koneksitabel (){
        try {
            Class.forName("com.mysq"
                    + "l.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql:"
                    + "//localhost:3306/database_rekam_medis", "root", "");
            System.out.println("KONEKSI BERHASIL");
            JOptionPane.showMessageDialog(null, "Selamat Datang\nDi Form Periksa");
            
        } catch (Exception e) {
            System.out.println("KONEKSI GAGAL \n"+e);
        }
    }
    
    private void tampildata(String sql){
        DefaultTableModel datalist = new DefaultTableModel();
        datalist.addColumn("No");
        datalist.addColumn("Nomor Periksa");
        datalist.addColumn("Tanggal Periksa");
        datalist.addColumn("Penyakit");
        datalist.addColumn("Kode Pasien");
        datalist.addColumn("Kode Dokter");
        datalist.addColumn("Kode Obat");
        datalist.addColumn("Jumlah Total");
        datalist.addColumn("Bayar");
        datalist.addColumn("Kembali");
        try {
            int i = 1;
            st=con.createStatement();
            RsPeriksa=st.executeQuery("SELECT * FROM tabel_periksa");
            while (RsPeriksa.next()){
                datalist.addRow(new Object[]{
                    (""+i++),RsPeriksa.getString(1), RsPeriksa.getString(2), 
                    RsPeriksa.getString(3), RsPeriksa.getString(4), RsPeriksa.getString(5), 
                    RsPeriksa.getString(6), RsPeriksa.getString(8), 
                    RsPeriksa.getString(9), RsPeriksa.getString(10)
                });
                data_periksa.setModel(datalist);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "GAGAL TAMPIL \n"+e.getMessage());
        }
    }
    
    private void DaftarObat(){
        kode_obat.removeAllItems();
        kode_obat.addItem("Pilih");
        try {
            String sql ="Select * FROM tabel_obat";
            Statement st=con.createStatement();
            RsObat=st.executeQuery(sql);
            while(RsObat.next()){
                String Alliasob=RsObat.getString("kode_obat");
                kode_obat.addItem(Alliasob);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan Kode Obat \n"
                +e.getMessage());
        }
    }
    
    private void DaftarPasien(){
        kode_pasien.removeAllItems();
        kode_pasien.addItem("Pilih");
        try {
            String sql ="Select * FROM tabel_pasien";
            Statement st=con.createStatement();
            RsPasien=st.executeQuery(sql);
            while(RsPasien.next()){
                String Alliasps=RsPasien.getString("kode_pasien");
                kode_pasien.addItem(Alliasps);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan Kode pasien \n"
                +e.getMessage());
        }
    }
    
    private void DaftarDoktor(){
        kode_dokter.removeAllItems();
        kode_dokter.addItem("Pilih");
        try {
            String sql ="Select * FROM tabel_dokter";
            Statement st=con.createStatement();
            RsDokter=st.executeQuery(sql);
            while(RsDokter.next()){
                String AlliasD=RsDokter.getString("kode_dokter");
                kode_dokter.addItem(AlliasD);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan Kode Dokter \n"
                +e.getMessage());
        }
    }
    
    private void prosestambah(){
        try {
            DefaultTableModel tableModel = (DefaultTableModel)data_data_obat.getModel();
            String[]data = new String[6];
            data[0] = String.valueOf(kode_obat.getSelectedItem());
            data[1] = nama_obat.getText();
            data[2] = harga_obat.getText();
            data[3] = jumlah_obat.getText();
            data[4] = total_harga.getText();
            tableModel.addRow(data);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Memasukkan Data \n" +e.getMessage());
        }
    }
        
    private void total(){
        int jumlahBaris = data_data_obat.getRowCount();
        int jumlahtotal =0, jumlahitem=0;
        int jumlahjual, totalharga;
        
        TableModel tblmodel;
        tblmodel = data_data_obat.getModel();
        for (int i=0; i<jumlahBaris; i++){
            jumlahjual = Integer.parseInt(tblmodel.getValueAt(i, 3).toString());
            jumlahitem = jumlahitem+jumlahjual;
            totalharga = Integer.parseInt(tblmodel.getValueAt(i, 4).toString());
            jumlahtotal=jumlahtotal+totalharga;}
           
            jumlah_total.setText(String.valueOf(jumlahtotal));
            jumlah_item.setText(String.valueOf(jumlahitem));
    }
        
    private void simpandetail(){
            int jumlah_baris = data_data_obat.getRowCount();
            if(jumlah_baris == 0){
                JOptionPane.showMessageDialog(rootPane, "Tabel Masih Kosong!");
            }else{
                try {
                    int i=0;
                    while(i < jumlah_baris){
                        st.executeUpdate("insert into tabel_detail_periksa"
                        + "(kode_periksa, kode_obat, nama_obat, harga_obat, jumlah_obat) "
                        + "values('"+kode_periksa.getText() +"', "
                        + "'"+data_data_obat.getValueAt(i, 0)+"',"
                        + "'"+data_data_obat.getValueAt(i, 1)+"',"
                        + "'"+data_data_obat.getValueAt(i, 2)+"',"
                        + "'"+data_data_obat.getValueAt(i, 3)+"')");
                    try {
                        sql="SELECT * FROM tabel_obat WHERE "
                                + "kode_obat='" + data_data_obat.getValueAt(i, 0) +"'";
                        st=con.createStatement();
                        RsObat=st.executeQuery(sql);
                        while(RsObat.next()){
                            try {
                                st=con.createStatement();
                                st.execute(sql);
                            }catch (Exception err) {
                                JOptionPane.showConfirmDialog(null, "Tidak Ada Barang Update!\n"+err.getMessage());
                            }
                        }
                        } catch (Exception se) {
                                JOptionPane.showConfirmDialog(null, "Data Tidak Ditemukan!!\n"+se.getMessage());
                                nama_pasien.requestFocus();
                                }
                    i++;
                        
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, "Gagal Menyimpan ! ERROR : \n"+e);
                }
            }
        }
    
    private void tampildetailpemeriksaan(String sql){
    DefaultTableModel datalist = new DefaultTableModel();
        datalist.addColumn("No");
        datalist.addColumn("No Periksa");
        datalist.addColumn("Kode Obat");
        datalist.addColumn("Nama Obat");
        datalist.addColumn("Harga Obat");
        datalist.addColumn("Jumlah Obat");
        try {
            int i = 1;
            st=con.createStatement();
            RsDetail=st.executeQuery("SELECT * FROM tabel_detail_periksa where kode_periksa='"+ no +"'");
            while (RsDetail.next()){
                datalist.addRow(new Object[]{
                    (""+i++),RsDetail.getString(1), RsDetail.getString(2), 
                    RsDetail.getString(3), RsDetail.getString(4), RsDetail.getString(5), 
                });
                data_data_obat.setModel(datalist);
        
            Btn_Kurang.setEnabled(false);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "GAGAL TAMPIL \n"+e.getMessage());
        }      
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        kode_periksa = new javax.swing.JTextField();
        nama_pasien = new javax.swing.JTextField();
        alamat_pasien = new javax.swing.JTextField();
        tanggal_periksa = new com.toedter.calendar.JDateChooser();
        kode_pasien = new javax.swing.JComboBox<>();
        btn_simpan = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();
        Btn_Tambah = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        no_hp_pasien = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        nama_dokter = new javax.swing.JTextField();
        kode_dokter = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        keahlian_dokter = new javax.swing.JTextField();
        penyakit_pasien = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        data_data_obat = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        kode_obat = new javax.swing.JComboBox<>();
        nama_obat = new javax.swing.JTextField();
        jenis_obat = new javax.swing.JTextField();
        Btn_Kurang = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jumlah_item = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jumlah_obat = new javax.swing.JTextField();
        jumlah_total = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jumlah_bayar = new javax.swing.JTextField();
        kembalian = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        harga_obat = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        data_periksa = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        total_harga = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        boder_atas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        boder_kiri = new javax.swing.JPanel();
        boder_bawah = new javax.swing.JPanel();
        boder_kanan = new javax.swing.JPanel();
        btn_hapus = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("No Periksa");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tanggal Periksa");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, -1, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nama Pasien");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Kode Doktor");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, -1, -1));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("No HP");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        kode_periksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kode_periksaKeyPressed(evt);
            }
        });
        jPanel1.add(kode_periksa, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 120, -1));
        jPanel1.add(nama_pasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 120, -1));
        jPanel1.add(alamat_pasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 120, -1));
        jPanel1.add(tanggal_periksa, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 120, -1));

        kode_pasien.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        kode_pasien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", " " }));
        kode_pasien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                kode_pasienItemStateChanged(evt);
            }
        });
        jPanel1.add(kode_pasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 120, -1));

        btn_simpan.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn_simpan.setText("SIMPAN");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        jPanel1.add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, 80, -1));

        btn_batal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn_batal.setText("BATAL");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });
        jPanel1.add(btn_batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 400, 90, -1));

        btn_keluar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn_keluar.setText("KELUAR");
        btn_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_keluarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 400, 90, -1));

        Btn_Tambah.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Btn_Tambah.setText("+");
        Btn_Tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_TambahActionPerformed(evt);
            }
        });
        jPanel1.add(Btn_Tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 260, 40, 20));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Alamat Pasien");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));
        jPanel1.add(no_hp_pasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 120, -1));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Kode Pasien");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));
        jPanel1.add(nama_dokter, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 200, 120, -1));

        kode_dokter.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        kode_dokter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        kode_dokter.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                kode_dokterItemStateChanged(evt);
            }
        });
        jPanel1.add(kode_dokter, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, 120, -1));

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nama Doktor");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, -1, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Keahlian");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, -1, -1));

        jLabel18.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Penyakit");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));
        jPanel1.add(keahlian_dokter, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 230, 120, -1));
        jPanel1.add(penyakit_pasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 120, -1));

        data_data_obat.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        data_data_obat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Obat", "Nama Obat", "Harga Obat", "Jumlah Obat", "Total Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        data_data_obat.setEnabled(false);
        jScrollPane2.setViewportView(data_data_obat);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 140, 350, 110));

        jLabel19.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Kode Obat");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, -1, -1));

        jLabel20.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Nama Obat");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 170, -1, -1));

        jLabel21.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Jenis Obat");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, -1, -1));

        kode_obat.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        kode_obat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        kode_obat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                kode_obatItemStateChanged(evt);
            }
        });
        jPanel1.add(kode_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 140, 120, -1));
        jPanel1.add(nama_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 170, 120, -1));
        jPanel1.add(jenis_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 200, 120, -1));

        Btn_Kurang.setText("-");
        Btn_Kurang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_KurangActionPerformed(evt);
            }
        });
        jPanel1.add(Btn_Kurang, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 260, 40, 20));

        jLabel22.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Jumlah Item Obat");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 260, -1, -1));
        jPanel1.add(jumlah_item, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 260, 120, -1));

        jLabel23.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Jumlah Obat");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 260, 80, 20));

        jumlah_obat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlah_obatActionPerformed(evt);
            }
        });
        jPanel1.add(jumlah_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 260, 120, -1));

        jumlah_total.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel1.add(jumlah_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 290, 120, -1));

        jLabel24.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Jumlah Total Biaya");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 290, -1, 20));

        jLabel25.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Jumlah Bayar");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 330, -1, -1));

        jLabel26.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Kembali");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 360, -1, -1));

        jumlah_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlah_bayarActionPerformed(evt);
            }
        });
        jPanel1.add(jumlah_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 330, 120, -1));
        jPanel1.add(kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 360, 120, -1));

        jLabel27.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Harga Obat");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, -1, -1));
        jPanel1.add(harga_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 230, 120, -1));

        data_periksa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Nomor Periksa", "Tangga Periksa", "Penyakit", "Kode Pasien", "Kode Dokter", "Kode Obat", "Jumlah Total", "Bayar", "Kembali"
            }
        ));
        data_periksa.setEnabled(false);
        jScrollPane3.setViewportView(data_periksa);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 1060, 110));

        jLabel28.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Total Harga");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 290, -1, 20));

        total_harga.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel1.add(total_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 290, 120, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1100, 10));

        boder_atas.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout boder_atasLayout = new javax.swing.GroupLayout(boder_atas);
        boder_atas.setLayout(boder_atasLayout);
        boder_atasLayout.setHorizontalGroup(
            boder_atasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        boder_atasLayout.setVerticalGroup(
            boder_atasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(boder_atas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 10));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("REKAM MEDIS RUMAH SAKIT UTY");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 600, 40));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo/logo-baru-uty-putih2-80x80.png"))); // NOI18N
        logo.setText(" ");
        jPanel1.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Jl. Ringroad Utara - Yogyakarta. D.I. 55285");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Telp. (0274) 623310, E-mail: info@uty.ac.id, Homepage: www.uty.ac.id");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, -1, -1));

        jLabel30.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Telp. (0274) 623310, E-mail: info@uty.ac.id, Homepage: www.uty.ac.id");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, -1, -1));

        boder_kiri.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout boder_kiriLayout = new javax.swing.GroupLayout(boder_kiri);
        boder_kiri.setLayout(boder_kiriLayout);
        boder_kiriLayout.setHorizontalGroup(
            boder_kiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        boder_kiriLayout.setVerticalGroup(
            boder_kiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(boder_kiri, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 560));

        boder_bawah.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout boder_bawahLayout = new javax.swing.GroupLayout(boder_bawah);
        boder_bawah.setLayout(boder_bawahLayout);
        boder_bawahLayout.setHorizontalGroup(
            boder_bawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        boder_bawahLayout.setVerticalGroup(
            boder_bawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(boder_bawah, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 1100, 10));

        boder_kanan.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout boder_kananLayout = new javax.swing.GroupLayout(boder_kanan);
        boder_kanan.setLayout(boder_kananLayout);
        boder_kananLayout.setHorizontalGroup(
            boder_kananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        boder_kananLayout.setVerticalGroup(
            boder_kananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(boder_kanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 0, 10, 720));

        btn_hapus.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn_hapus.setText("HAPUS");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        jPanel1.add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 80, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kode_periksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_periksaKeyPressed
        // TODO add your handling code here:
        no=kode_periksa.getText();
        int tekanenter=evt.getKeyCode();
        if (tekanenter==10){
            try {
                sql="Select * from tabel_periksa "
                + "where kode_periksa='"+ no +"'";
                st=con.createStatement();
                RsPeriksa=st.executeQuery(sql);
                while (RsPeriksa.next()) {
                    tanggal_periksa.setDate(RsPeriksa.getDate("tanggal_periksa"));
                    kode_dokter.setSelectedItem(RsPeriksa.getString("kode_dokter"));
                    kode_pasien.setSelectedItem(RsPeriksa.getString("kode_pasien"));
                    penyakit_pasien.setText(RsPeriksa.getString("penyakit"));
                    kode_obat.setSelectedItem(RsPeriksa.getString("kode_obat"));
                    jumlah_item.setText(RsPeriksa.getString("jumlah_item"));
                    jumlah_total.setText(RsPeriksa.getString("jumlah_total"));
                    jumlah_bayar.setText(RsPeriksa.getString("jumlah_bayar"));
                    kembalian.setText(RsPeriksa.getString("kembalian"));
                    tampildetailpemeriksaan("select * from tabel_detail_periksa");
                    JOptionPane.showMessageDialog(null,
                        "Data Ditemukan");
                    Btn_Tambah.setEnabled(false);
                    btn_simpan.setEnabled(false);
                    jumlah_obat.setEnabled(false);
                    total_harga.setEnabled(false);
                    form_disable();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Tidak Ditemukan \n"+e.getMessage());
                kode_periksa.requestFocus();
            }
        }
    }//GEN-LAST:event_kode_periksaKeyPressed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        String Tanggal,penyakit;
        int totalItem, totalobat, bayar, kembali, totalbayar;
        
        no=String.valueOf(kode_periksa.getText());
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
        Tanggal=format.format(tanggal_periksa.getDate());
        kodepas=kode_pasien.getItemAt(kode_pasien.getSelectedIndex()).toString();
        kodedok=kode_dokter.getItemAt(kode_dokter.getSelectedIndex()).toString();
        kodeobat=kode_obat.getItemAt(kode_obat.getSelectedIndex()).toString();
        penyakit=String.valueOf(penyakit_pasien.getText());
        
        totalItem=Integer.parseInt(jumlah_item.getText());
        totalobat=Integer.parseInt(jumlah_obat.getText());
        bayar=Integer.parseInt(jumlah_bayar.getText());
        kembali=Integer.parseInt(kembalian.getText());
        totalbayar=Integer.parseInt(jumlah_total.getText());
        simpandetail();
        try {
            sql="INSERT INTO tabel_periksa(kode_periksa, "
            + "tanggal_periksa, "
            + "kode_dokter, "
            + "kode_pasien, "
            + "penyakit, "
            + "kode_obat, "
            + "jumlah_item, "
            + "jumlah_total, "
            + "jumlah_bayar, "
            + "kembalian)VALUES"
            + "('"+ no +"',"
            + "'"+ Tanggal +"',"
            + "'"+ kodedok +"',"
            + "'"+ kodepas +"',"
            + "'"+ penyakit +"',"
            + "'"+ kodeobat +"',"
            + "'"+ totalItem +"',"
            + "'"+ totalbayar +"',"
            + "'"+ bayar +"',"
            + "'"+ kembali +"')";
            st=con.createStatement();
            st.execute(sql);
            tampildata("Select * from tabel_periksa");
            form_clear();
            JOptionPane.showMessageDialog(null,
                "Data Tersimpan");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan \n"+e.getMessage());
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        // TODO add your handling code here:
        form_clear();
        form_enable();
        Btn_Tambah.setEnabled(true);
        btn_simpan.setEnabled(true);
        Btn_Kurang.setEnabled(true);
        kode_periksa.requestFocus();
        kode_periksa.setEnabled(true);
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_keluarActionPerformed
        // TODO add your handling code here:
        new menu().setVisible(true);
        this.setVisible(false);
        //dispose();
    }//GEN-LAST:event_btn_keluarActionPerformed

    private void kode_pasienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_kode_pasienItemStateChanged
        // TODO add your handling code here:
         try {
            sql="Select * FROM tabel_pasien WHERE "
                    + "kode_pasien ='" + kode_pasien.getSelectedItem() +"'";
            st=con.createStatement();
            RsPasien=st.executeQuery(sql);
                while(RsPasien.next()){
                    nama_pasien.setText(RsPasien.getString("nama_pasien"));
                     alamat_pasien.setText(RsPasien.getString("alamat_pasien"));
                     no_hp_pasien.setText(RsPasien.getString("no_hp_pasien"));           
                }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan Detail Pelanggan \n"
                +e.getMessage());
        }
    }//GEN-LAST:event_kode_pasienItemStateChanged

    private void kode_dokterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_kode_dokterItemStateChanged
        // TODO add your handling code here:
        try {
            sql="Select * FROM tabel_dokter WHERE "
                    + "kode_dokter='" + kode_dokter.getSelectedItem() +"'";
            st=con.createStatement();
            RsDokter=st.executeQuery(sql);
                while(RsDokter.next()){
                    nama_dokter.setText(RsDokter.getString("nama_dokter"));
                     keahlian_dokter.setText(RsDokter.getString("keahlian_dokter"));
                                   
                }
                
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan Detail dokter \n"
                +e.getMessage());
        }
    }//GEN-LAST:event_kode_dokterItemStateChanged

    private void kode_obatItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_kode_obatItemStateChanged
        // TODO add your handling code here:
         try {
            sql="Select * FROM tabel_obat WHERE "
                    + "kode_obat='" + kode_obat.getSelectedItem() +"'";
            st=con.createStatement();
            RsObat=st.executeQuery(sql);
                while(RsObat.next()){
                    nama_obat.setText(RsObat.getString("nama_obat"));
                     jenis_obat.setText(RsObat.getString("jenis_obat"));
                     harga_obat.setText(RsObat.getString("harga_obat"));
                                   
                }
                
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan Detail Pelanggan \n"
                +e.getMessage());
        }
    }//GEN-LAST:event_kode_obatItemStateChanged

    private void Btn_KurangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_KurangActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) data_data_obat.getModel();
        int row = data_data_obat.getSelectedRow();
        if(row>=0){
            int oke=JOptionPane.showConfirmDialog(null,
                "Yakin Mau Hapus?","Konfirmasi",
                JOptionPane.YES_NO_OPTION);
            if(oke==0){
                model.removeRow(row);
            }
        }
    }//GEN-LAST:event_Btn_KurangActionPerformed

    private void Btn_TambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_TambahActionPerformed
        // TODO add your handling code here:
        prosestambah();
        total();
    }//GEN-LAST:event_Btn_TambahActionPerformed

    private void jumlah_obatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlah_obatActionPerformed
        // TODO add your handling code here:
        hargaobat=Integer.parseInt(harga_obat.getText());
        jumlahobat=Integer.parseInt(jumlah_obat.getText());
        jumlahtotal=hargaobat*jumlahobat;
        total_harga.setText(String.valueOf(jumlahtotal));
    }//GEN-LAST:event_jumlah_obatActionPerformed

    private void jumlah_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlah_bayarActionPerformed
        // TODO add your handling code here:
        jumlahtotal=Integer.parseInt(jumlah_total.getText());
        bayar=Integer.parseInt(jumlah_bayar.getText());
        kembali=bayar-jumlahtotal;
        kembalian.setText(String.valueOf(kembali));
    }//GEN-LAST:event_jumlah_bayarActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        no=String.valueOf(kode_periksa.getText());
      
        try {
            sql= "DELETE from tabel_periksa where kode_periksa = '"+no+"'";
            st=con.createStatement();
            st.execute(sql);
            tampildata("Select * from tabel_periksa");
            form_clear();
            JOptionPane.showMessageDialog(null, 
                    "Data Berhasil Dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus \n"+e.getMessage());
        }
    }//GEN-LAST:event_btn_hapusActionPerformed
/**/
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(periksa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(periksa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(periksa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(periksa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new periksa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Kurang;
    private javax.swing.JButton Btn_Tambah;
    private javax.swing.JTextField alamat_pasien;
    private javax.swing.JPanel boder_atas;
    private javax.swing.JPanel boder_bawah;
    private javax.swing.JPanel boder_kanan;
    private javax.swing.JPanel boder_kiri;
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_keluar;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JTable data_data_obat;
    private javax.swing.JTable data_periksa;
    private javax.swing.JTextField harga_obat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jenis_obat;
    private javax.swing.JTextField jumlah_bayar;
    private javax.swing.JTextField jumlah_item;
    private javax.swing.JTextField jumlah_obat;
    private javax.swing.JTextField jumlah_total;
    private javax.swing.JTextField keahlian_dokter;
    private javax.swing.JTextField kembalian;
    private javax.swing.JComboBox<String> kode_dokter;
    private javax.swing.JComboBox<String> kode_obat;
    private javax.swing.JComboBox<String> kode_pasien;
    private javax.swing.JTextField kode_periksa;
    private javax.swing.JLabel logo;
    private javax.swing.JTextField nama_dokter;
    private javax.swing.JTextField nama_obat;
    private javax.swing.JTextField nama_pasien;
    private javax.swing.JTextField no_hp_pasien;
    private javax.swing.JTextField penyakit_pasien;
    private com.toedter.calendar.JDateChooser tanggal_periksa;
    private javax.swing.JTextField total_harga;
    // End of variables declaration//GEN-END:variables
}
