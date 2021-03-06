package rekam_medis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author ACER
 */

public class obat extends javax.swing.JFrame {
private Connection con;
private Statement st;
private ResultSet RsObat;
private String sql="";

private String kode, nama, jenis;
public Integer harga;

    public obat() {
        initComponents();
        koneksitabel();
        this.setLocationRelativeTo(null);
        tampildata("SELECT * FROM tabel_obat");
    }
    
    private void form_enable(){
        kode_obat.setEnabled(true);
        nama_obat.setEnabled(true);
        jenis_obat.setEnabled(true);
        harga_obat.setEnabled(true);
        kadaluarsa_obat.setEnabled(true);
    }
        
    private void form_clear(){
        kode_obat.setText("");
        nama_obat.setText("");
        jenis_obat.setText("");
        harga_obat.setText("");
        kadaluarsa_obat.setDate(null);
    }
    
    private void aksi_tambah(){
        form_enable();
        btn_tambah.setEnabled(true);
        btn_simpan.setEnabled(true);
        btn_edit.setEnabled(true);
        btn_batal.setEnabled(true);
        btn_hapus.setEnabled(false);
        
        kode_obat.setEnabled(true);
        kode_obat.requestFocus(true);
    }
        
    private void koneksitabel (){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/database_rekam_medis", "root", "");
            System.out.println("KONEKSI BERHASIL");
            JOptionPane.showMessageDialog(null, "Selamat Datang\nDi Form Obat");
            
        } catch (Exception e) {
            System.out.println("KONEKSI GAGAL \n"+e);
        }
    }
    
    private void tampildata(String sql){
        DefaultTableModel datalist = new DefaultTableModel();
        datalist.addColumn("No");
        datalist.addColumn("Kode Obat");
        datalist.addColumn("Nama");
        datalist.addColumn("Jenis");
        datalist.addColumn("Harga");
        datalist.addColumn("Kadaluarsa");
        try {
            int i = 1;
            st=con.createStatement();
            RsObat=st.executeQuery("SELECT * FROM tabel_obat");
            while (RsObat.next()){
                datalist.addRow(new Object[]{
                    (""+i++),RsObat.getString(1), RsObat.getString(2), 
                    RsObat.getString(3),RsObat.getInt(4), RsObat.getString(5)
                });
                data_obat.setModel(datalist);
            }
            
        }catch (Exception e) {
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        kode_obat = new javax.swing.JTextField();
        nama_obat = new javax.swing.JTextField();
        jenis_obat = new javax.swing.JTextField();
        harga_obat = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        data_obat = new javax.swing.JTable();
        btn_tambah = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        boder_kiri = new javax.swing.JPanel();
        boder_kanan = new javax.swing.JPanel();
        boder_bawah = new javax.swing.JPanel();
        boder_atas = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        kadaluarsa_obat = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 890, 10));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("REKAM MEDIS RUMAH SAKIT UTY");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 600, 40));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Jl. Ringroad Utara - Yogyakarta. D.I. 55285");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Telp. (0274) 623310, E-mail: info@uty.ac.id, Homepage: www.uty.ac.id");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Kode obat");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, 20));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nama");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 40, 20));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Jenis");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 90, 20));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Harga");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 70, 20));
        jPanel1.add(kode_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 180, -1));
        jPanel1.add(nama_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 180, -1));

        jenis_obat.setText(" ");
        jPanel1.add(jenis_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 180, 30));

        harga_obat.setText(" ");
        jPanel1.add(harga_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 180, -1));

        data_obat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Kode obat", "Nama", "Jenis", "Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        data_obat.setEnabled(false);
        data_obat.setRowSelectionAllowed(false);
        jScrollPane1.setViewportView(data_obat);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, 530, 170));

        btn_tambah.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn_tambah.setText("Tambah");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        jPanel1.add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, -1, -1));

        btn_simpan.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        jPanel1.add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 340, 80, -1));

        btn_edit.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn_edit.setText("Edit");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });
        jPanel1.add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 340, 80, -1));

        btn_hapus.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        jPanel1.add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 340, 80, -1));

        btn_batal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn_batal.setText("Batal");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });
        jPanel1.add(btn_batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 340, 80, -1));

        btn_keluar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn_keluar.setText("Keluar");
        btn_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_keluarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 340, 80, -1));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo/logo-baru-uty-putih2-80x80.png"))); // NOI18N
        logo.setText(" ");
        jPanel1.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

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

        jPanel1.add(boder_kiri, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 390));

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

        jPanel1.add(boder_kanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 0, 10, 400));

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

        jPanel1.add(boder_bawah, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 900, 10));

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

        jPanel1.add(boder_atas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 10));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Kadaluarsa");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));
        jPanel1.add(kadaluarsa_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 180, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_keluarActionPerformed
        // TODO add your handling code here:
        new menu().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_keluarActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        kode=String.valueOf(kode_obat.getText());
      
        try {
            sql= "DELETE from tabel_obat where kode_obat = '"+kode+"'";
            st=con.createStatement();
            st.execute(sql);
            tampildata("Select * from tabel_obat");
            form_clear();
            JOptionPane.showMessageDialog(null, 
                    "Data Berhasil Dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus \n"+e.getMessage());
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        aksi_tambah();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        // TODO add your handling code here:
        form_clear();
        btn_tambah.setEnabled(true);
        btn_simpan.setEnabled(true);
        kode_obat.requestFocus();
        btn_hapus.setEnabled(true);
        kode_obat.setEnabled(true);
    }//GEN-LAST:event_btn_batalActionPerformed
        
    private void kode_obatKeyPressed(java.awt.event.KeyEvent evt) {                                          
        // TODO add your handling code here:
        kode=kode_obat.getText();
        int tekanenter=evt.getKeyCode();
        if (tekanenter==10){
            try {
                sql="Select * from tabel_obat "
                        + "where kode_obat='"+ kode +"'";
                st=con.createStatement();
                RsObat=st.executeQuery(sql);
                while (RsObat.next()) {
                   nama_obat.setText(RsObat.getString("nama_obat"));
                   jenis_obat.setText(RsObat.getString("jenis_obat"));
                   harga_obat.setText(RsObat.getString("harga_obat"));
                   kadaluarsa_obat.setDate(RsObat.getDate("kadaluarsa_obat"));
                   JOptionPane.showMessageDialog(null,
                           "Data Ditemukan");
                   btn_tambah.setEnabled(false);
                   btn_simpan.setEnabled(false);
                   btn_hapus.setEnabled(true);
                   kode_obat.setEnabled(false);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Tidak Ditemukan \n"+e.getMessage());
                kode_obat.requestFocus();
            }
        }
    }
    
    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        String Tanggal;
       
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
        Tanggal=format.format(kadaluarsa_obat.getDate());
        kode=String.valueOf(kode_obat.getText());
        nama=String.valueOf(nama_obat.getText());
        jenis=String.valueOf(jenis_obat.getText());
        harga=Integer.parseInt(harga_obat.getText());
        

        try {
            sql="INSERT INTO tabel_obat(kode_obat, "
                    + "nama_obat, "
                    + "jenis_obat, "
                    + "kadaluarsa_obat, "
                    + "harga_obat)VALUES"                   
                    + "('"+ kode +"',"
                    + "'"+ nama +"',"
                    + "'"+ jenis +"',"
                    + "'"+ Tanggal + "',"
                    + "'"+ harga +"')";
            st=con.createStatement();
            st.execute(sql);
            tampildata("Select * from tabel_obat");
            form_clear();
            JOptionPane.showMessageDialog(null, "Data Tersimpan");
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan \n"+e.getMessage());
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    
    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
        String Tanggal;
       
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
        Tanggal=format.format(kadaluarsa_obat.getDate());
        kode=String.valueOf(kode_obat.getText());
        nama=String.valueOf(nama_obat.getText());
        jenis=String.valueOf(jenis_obat.getText());
        harga=Integer.parseInt(harga_obat.getText());
        try {
            sql ="UPDATE tabel_obat set nama_obat= '"+nama+"', "
                        + "jenis_obat = '"+jenis_obat+"', harga_obat = '"+harga+"', "
                        + "kadaluarsa_obat = '"+Tanggal+"' "
                        + "where kode_obat ='"+kode+"'";
            st=con.createStatement();
            st.execute(sql);
            tampildata("Select * from tabel_obat");
            form_clear();
            JOptionPane.showMessageDialog(null, 
                    "Data Berhasil Diubah");
         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah \n"+e.getMessage());
        }
    }//GEN-LAST:event_btn_editActionPerformed

     /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(obat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(obat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(obat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(obat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new obat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boder_atas;
    private javax.swing.JPanel boder_bawah;
    private javax.swing.JPanel boder_kanan;
    private javax.swing.JPanel boder_kiri;
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_keluar;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JTable data_obat;
    private javax.swing.JTextField harga_obat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jenis_obat;
    private com.toedter.calendar.JDateChooser kadaluarsa_obat;
    private javax.swing.JTextField kode_obat;
    private javax.swing.JLabel logo;
    private javax.swing.JTextField nama_obat;
    // End of variables declaration//GEN-END:variables
}
