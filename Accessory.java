/**
 * فئة الملحق (Accessory)
 * تمثل بيانات الجهاز الواحد في المتجر
 * 
 * مهام الشخص الأول:
 * - تصميم فئة Accessory بالخصائص المطلوبة
 * - تطبيق مبدأ Encapsulation بجعل السعر (price) private
 * - توفير الـ Getters و Setters المطلوبة
 */

public class Accessory {
    // الخصائص الأساسية للملحق
    private int id;              // رقم تعريفي فريد
    private String company;      // اسم العلامة التجارية
    private int type;            // نوع الجهاز (1: ماوس، 2: لوحة مفاتيح، 3: شاشة، 4: طابعة)
    private int quantity;        // الكمية المتوفرة
    private double price;        // السعر (خاص - Encapsulation)

    /**
     * منشئ الفئة
     */
    public Accessory(int id, String company, int type, int quantity, double price) {
        this.id = id;
        this.company = company;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }

    // ============ Getters ============
    public int getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public int getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    // السعر خاص - Encapsulation
    public double getPrice() {
        return price;
    }

    // ============ Setters ============
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        }
    }

    /**
     * تحويل رقم النوع إلى اسم وصفي
     */
    public String getTypeName() {
        switch (type) {
            case 1:
                return "ماوس";
            case 2:
                return "لوحة مفاتيح";
            case 3:
                return "شاشة";
            case 4:
                return "طابعة";
            default:
                return "غير معروف";
        }
    }

    /**
     * تمثيل نصي للملحق
     */
    @Override
    public String toString() {
        return String.format(
            "| رقم: %-3d | العلامة: %-10s | النوع: %-12s | الكمية: %-3d | السعر: %.2f |",
            id, company, getTypeName(), quantity, price
        );
    }
}
