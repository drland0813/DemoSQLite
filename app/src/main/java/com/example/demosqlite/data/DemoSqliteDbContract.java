package com.example.demosqlite.data;
import android.provider.BaseColumns;
public class DemoSqliteDbContract {

    static public class SinhVienEntry implements BaseColumns {
        /**
         * Name of database table for SinhVien
         */
        static public final String TABLE_NAME = "SinhVien";

        /**
         * Unique ID number for the SinhVien (only for use in the database table).
         * <p>
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Student's full name.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMNS_FULL_NAME = "fullName";

        /**
         * Student's class id.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMNS_CLASS_ID = "classId";
    }
}
