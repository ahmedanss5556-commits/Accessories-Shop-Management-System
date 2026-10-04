import java.util.ArrayList;

/**
 * فئة المتجر (Store)
 * تدير العمليات المختلفة على الملحقات
 * 
 * مهام الشخص الثاني (إدارة الإضافة والتحديث والحذف):
 * - إضافة ملحقات جديدة
 * - تحديث الكمية والسعر
 * - حذف ملحق
 * 
 * مهام الشخص الثالث (البيع والتقارير):
 * - بيع الملحقات مع التحقق من الشروط
 * - عرض التقارير بأنواعها المختلفة
 */

public class Store {
    private ArrayList<Accessory> accessories;

    /**
     * منشئ المتجر
     */
    public Store() {
        this.accessories = new ArrayList<>();
    }

    // ============================================
    // مهام الشخص الثاني: الإضافة والتحديث والحذف
    // ============================================

    /**
     * البحث عن ملحق برقمه التعريفي
     */
    public Accessory searchById(int id) {
        for (Accessory a : accessories) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    /**
     * إضافة ملحق جديد إلى المتجر
     * مهمة الشخص الثاني - Add new Accessory
     */
    public void addNewAccessory(int id, String company, int type, int quantity, double price) {
        if (searchById(id) != null) {
            System.out.println("❌ خطأ: رقم تعريفي موجود بالفعل!");
            return;
        }
        accessories.add(new Accessory(id, company, type, quantity, price));
        System.out.println("✓ تم إضافة الملحق بنجاح.");
    }

    /**
     * تحديث كمية وسعر ملحق معين
     * مهمة الشخص الثاني - Update Accessory (quantity, price)
     */
    public void updateAccessory(int id, int newQuantity, double newPrice) {
        Accessory acc = searchById(id);
        if (acc == null) {
            System.out.println("❌ خطأ: الملحق غير موجود!");
            return;
        }

        if (newQuantity < 0 || newPrice < 0) {
            System.out.println("❌ خطأ: الكمية والسعر يجب أن تكون موجبة!");
            return;
        }

        acc.setQuantity(newQuantity);
        acc.setPrice(newPrice);
        System.out.println("✓ تم تحديث الملحق بنجاح.");
    }

    /**
     * حذف ملحق من المتجر
     * مهمة الشخص الثاني - Delete Accessory
     */
    public void deleteAccessory(int id) {
        Accessory acc = searchById(id);
        if (acc == null) {
            System.out.println("❌ خطأ: الملحق غير موجود!");
            return;
        }
        accessories.remove(acc);
        System.out.println("✓ تم حذف الملحق بنجاح.");
    }

    // ============================================
    // مهام الشخص الثالث: البيع والتقارير
    // ============================================

    /**
     * بيع ملحق مع التحقق من الشروط
     * مهمة الشخص الثالث - Sell Accessory
     * 
     * الشرو��:
     * 1. إذا كانت الكمية المطلوبة أكبر من الكمية الموجودة => رسالة خطأ
     * 2. إذا لم يكن الجهاز موجوداً => رسالة خطأ
     * 3. حساب السعر الإجمالي
     * 4. تحديث الكمية الموجودة
     * 5. إذا وصلت الكمية ≤ 5 => تحذير
     */
    public void sellAccessory(int id, int quantity) {
        Accessory acc = searchById(id);

        // الشرط 2: التحقق من وجود الملحق
        if (acc == null) {
            System.out.println("❌ خطأ: الملحق غير موجود!");
            return;
        }

        // الشرط 1: التحقق من الكمية
        if (quantity > acc.getQuantity()) {
            System.out.println("❌ خطأ: الكمية المطلوبة أكبر من الكمية الموجودة!");
            System.out.println("   الكمية المتوفرة: " + acc.getQuantity());
            return;
        }

        // الشرط 3: حساب السعر الإجمالي
        double totalPrice = quantity * acc.getPrice();

        // الشرط 4: تحديث الكمية
        acc.setQuantity(acc.getQuantity() - quantity);

        // عرض تفاصيل البيع
        System.out.println("\n════════════════════════════════════");
        System.out.println("✓ تمت عملية البيع بنجاح");
        System.out.println("════════════════════════════════════");
        System.out.println("الملحق: " + acc.getTypeName());
        System.out.println("العلامة التجارية: " + acc.getCompany());
        System.out.println("الكمية المباعة: " + quantity);
        System.out.println("السعر الواحد: " + acc.getPrice());
        System.out.println("السعر الإجمالي: " + totalPrice);
        System.out.println("الكمية المتبقية: " + acc.getQuantity());

        // الشرط 5: التحذير من الكمية الحرجة
        if (acc.getQuantity() <= 5) {
            System.out.println("⚠️  تحذير: هذا الملحق وصل إلى الحد الحرج!");
        }
        System.out.println("════════════════════════════════════\n");
    }

    /**
     * عرض جميع الملحقات
     * مهمة الشخص الثالث - View Reports - All Accessories Report
     */
    public void printAllAccessories() {
        if (accessories.isEmpty()) {
            System.out.println("❌ لا توجد ملحقات في المتجر.");
            return;
        }

        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║              تقرير جميع الملحقات الموجودة في المتجر");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        for (Accessory a : accessories) {
            System.out.println(a);
        }
        System.out.println("\nإجمالي الملحقات: " + accessories.size());
    }

    /**
     * عرض الملحقات بنوع وسعر محددين
     * مهمة الشخص الثالث - View Reports - Accessories with specific type and price
     */
    public void printAccessoriesByTypeAndPrice(int type, double priceLimit) {
        ArrayList<Accessory> filtered = new ArrayList<>();

        for (Accessory a : accessories) {
            if (a.getType() == type && a.getPrice() <= priceLimit) {
                filtered.add(a);
            }
        }

        if (filtered.isEmpty()) {
            System.out.println("❌ لا توجد ملحقات بهذا النوع والسعر.");
            return;
        }

        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║        تقرير الملحقات حسب النوع والسعر");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println("النوع: " + getTypeNameStatic(type));
        System.out.println("السعر الأقصى: " + priceLimit);
        System.out.println();

        for (Accessory a : filtered) {
            System.out.println(a);
        }
        System.out.println("\nعدد النتائج: " + filtered.size());
    }

    /**
     * عرض الملحقات التي كميتها أقل من أو تساوي حد الطلب
     * مهمة الشخص الثالث - View Reports - Order Limit
     */
    public void printAccessoriesWithLowQuantity(int orderLimit) {
        ArrayList<Accessory> filtered = new ArrayList<>();
        boolean hasWarning = false;

        for (Accessory a : accessories) {
            if (a.getQuantity() <= orderLimit) {
                filtered.add(a);
                if (a.getQuantity() <= 5) {
                    hasWarning = true;
                }
            }
        }

        if (filtered.isEmpty()) {
            System.out.println("✓ جميع الملحقات بكمية كافية.");
            return;
        }

        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║    تقرير الملحقات ذات الكمية القليلة (حد الطلب)");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println("حد الطلب: " + orderLimit);
        System.out.println();

        for (Accessory a : filtered) {
            System.out.println(a);
        }

        if (hasWarning) {
            System.out.println("\n⚠️  تحذير: واحد أو أكثر من الملحقات وصل إلى الحد الحرج (≤ 5)!");
        }
        System.out.println("\nعدد الملحقات برقم الطلب: " + filtered.size());
    }

    /**
     * دالة مساعدة لتحويل رقم النوع إلى اسم (ثابتة)
     */
    private static String getTypeNameStatic(int type) {
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
     * الحصول على قائمة الملحقات (للاستخدام الداخلي)
     */
    public ArrayList<Accessory> getAccessories() {
        return accessories;
    }
}
