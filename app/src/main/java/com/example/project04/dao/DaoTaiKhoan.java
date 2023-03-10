package com.example.project04.dao;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project04.database.DatabaseHelper;
import com.example.project04.model.TaikhoanMatKhau;
import java.util.ArrayList;

public class DaoTaiKhoan {
    DatabaseHelper dtbRegister;
    public DaoTaiKhoan(Context context) {
        dtbRegister = new DatabaseHelper(context);
    }
    public ArrayList<TaikhoanMatKhau> getALl() {
        ArrayList<TaikhoanMatKhau> listTK = new ArrayList<>();
        SQLiteDatabase dtb = dtbRegister.getReadableDatabase();
        Cursor cs = dtb.rawQuery("SELECT * FROM taiKhoan", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            try {
                String tk = cs.getString(0);
                String mk = cs.getString(1);
                TaikhoanMatKhau t = new TaikhoanMatKhau(tk, mk);
                listTK.add(t);
                cs.moveToNext();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        cs.close();
        return listTK;
    }
    public boolean Insert(TaikhoanMatKhau tk) {
        SQLiteDatabase db = dtbRegister.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenTaiKhoan", tk.getTenTaiKhoan());
        values.put("matKhau", tk.getMatKhau());
        long r = db.insert("taiKhoan", null, values);
        if (r <= 0) {
            return false;
        }
        return true;
    }
    public boolean Update(TaikhoanMatKhau tk) {
        SQLiteDatabase db = dtbRegister.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("matKhau", tk.getMatKhau());
        int r = db.update("taiKhoan", values, "tenTaiKhoan = ?", new String[]{tk.getTenTaiKhoan()});
        if (r <= 0) {
            return false;
        }
        return true;
    }
}
