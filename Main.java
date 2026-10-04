import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * ═══════════════════════════════════════════════════════════════════════════
 * نظام إدارة محل الملحقات
 * Accessories Shop Management System
 * ═══════════════════════════════════════════════════════════════════════════
 * 
 * المقرر: البرمجة الكائنية التوجه (Object-Oriented Programming)
 * التاريخ: 6 أكتوبر 2026
 * 
 * تقسيم العمل:
 * ───────────────────────────────────────────────────────────────────────────
 * الشخص الأول:
 *   - تصميم فئة Accessory
 *   - تطبيق خصائص البيانات (ID, Company, Type, Quantity, Price)
 *   - تطبيق مبدأ Encapsulation (السعر خاص - private)
 *   - توفير الـ Getters و Setters
 * 
 * الشخص الثاني:
 *   - خيار 1: إضافة ملحق جديد
 *   - خيار 2: تحديث الملحق (الكمية والسعر)
 *   - خيار 5: حذف ملحق
 * 
 * الشخص الثالث:
 *   - خيار 3: بيع ملحق مع التحقق من الشروط
 *   - خيار 4: عرض التقارير
 *     أ) تقرير جميع الملحقات
 *     ب) تقرير الملحقات حسب النوع والسعر
 *     ج) تقرير الملحقات برقم الطلب (Order Limit)
 * ═══════════════════════════════════════════════════════════════════════════
 */

public class Main {
    private static Store store;
    private static Scanner input;

    public static void main(String[] args) {
        store = new Store();
        input = new Scanner(System.in);

        // إضافة بيانات افتراضية للاختبار
        initializeSampleData();

        displayWelcome();
        mainMenu();

        input.close();
    }

    /**
     * إضافة بيانات افتراضية للاختبار
     */
    private static void initializeSampleData() {
        store.addNewAccessory(1, "Logitech", 1, 50, 25.99);    // ماوس
        store.addNewAccessory(2, "Corsair", 2, 30, 79.99);     // لوحة مفاتيح
        store.addNewAccessory(3, "Dell", 3, 10, 199.99);       // شاشة
        store.addNewAccessory(4, "HP", 4, 5, 299.99);          // طابعة
        store.addNewAccessory(5, "Razer", 1, 3, 45.99);        // ماوس
    }

    /**
     * عرض رسالة الترحيب
     */
    private static void displayWelcome() {
        System.out.println("\n");
        System.out.println("╔═════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                     ║");
        System.out.println("║          مرحباً بك في نظام إدارة محل الملحقات                       ║");
        System.out.println("║          Welcome to Accessories Shop Management System            ║");
        System.out.println("║                                                                     ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════╝");
        System.out.println("\nالتاريخ: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println();
    }

    /**
     * القائمة الرئيسية
     */
    private static void mainMenu() {
        while (true) {
            displayMenu();
            System.out.print("\nاختر خياراً: ");
            int choice = getValidInput();

            switch (choice) {
                case 1:
                    // مهمة الشخص الثاني
                    addNewAccessory();
                    break;

                case 2:
                    // مهمة الشخص الثاني
                    updateAccessory();
                    break;

                case 3:
                    // مهمة الشخص الثالث
                    sellAccessory();
                    break;

                case 4:
                    // مهمة الشخص الثالث
                    viewReports();
                    break;

                case 5:
                    // مهمة الشخص الثاني
                    deleteAccessory();
                    break;

                case 6:
                    exitProgram();
                    return;

                default:
                    System.out.println("\n❌ خيار غير صحيح. يرجى المحاولة مجدداً.");
            }
        }
    }

    /**
     * عرض قائمة الخيارات
     */
    private static void displayMenu() {
        System.out.println("\n════════════════════════════════════════════════════════════════════");
        System.out.println("                        القائمة الرئيسية");
        System.out.println("═════════════════════════════════════════════════════��══════════════");
        System.out.println("1 - إضافة ملحق جديد                   (الشخص الثاني)");
        System.out.println("2 - تحديث ملحق (الكمية والسعر)          (الشخص الثاني)");
        System.out.println("3 - بيع ملحق                          (الشخص الثالث)");
        System.out.println("4 - عرض التقارير                      (الشخص الثالث)");
        System.out.println("5 - حذف ملحق                          (الشخص الثاني)");
        System.out.println("6 - خروج");
        System.out.println("════════════════════════════════════════════════════════════════════");
    }

    /**
     * الخيار 1: إضافة ملحق جديد (الشخص الثاني)
     */
    private static void addNewAccessory() {
        System.out.println("\n>>> إضافة ملحق جديد");

        System.out.print("كم عدد الملحقات التي تريد إضافتها؟ ");
        int count = getValidInput();

        for (int i = 0; i < count; i++) {
            System.out.println("\n--- الملحق " + (i + 1) + " ---");

            System.out.print("رقم التعريفي: ");
            int id = getValidInput();

            input.nextLine(); // استهلاك السطر الجديد
            System.out.print("اسم العلامة التجارية: ");
            String company = input.nextLine();

            System.out.println("نوع الجهاز:");
            System.out.println("  1 - ماوس");
            System.out.println("  2 - لوحة مفاتيح");
            System.out.println("  3 - شاشة");
            System.out.println("  4 - طابعة");
            System.out.print("اختر النوع: ");
            int type = getValidInput();

            System.out.print("الكمية: ");
            int quantity = getValidInput();

            System.out.print("السعر: ");
            double price = getValidDoubleInput();

            store.addNewAccessory(id, company, type, quantity, price);
        }
    }

    /**
     * الخيار 2: تحديث ملحق (الشخص الثاني)
     */
    private static void updateAccessory() {
        System.out.println("\n>>> تحديث ملحق");

        System.out.print("أدخل رقم التعريفي للملحق المراد تحديثه: ");
        int id = getValidInput();

        System.out.print("الكمية الجديدة: ");
        int newQuantity = getValidInput();

        System.out.print("السعر الجديد: ");
        double newPrice = getValidDoubleInput();

        store.updateAccessory(id, newQuantity, newPrice);
    }

    /**
     * الخيار 5: حذف ملحق (الشخص الثاني)
     */
    private static void deleteAccessory() {
        System.out.println("\n>>> حذف ملحق");

        System.out.print("أدخل رقم التعريفي للملحق المراد حذفه: ");
        int id = getValidInput();

        store.deleteAccessory(id);
    }

    /**
     * الخيار 3: بيع ملحق (الشخص الثالث)
     */
    private static void sellAccessory() {
        System.out.println("\n>>> بيع ملحق");

        System.out.print("أدخل رقم التعريفي للملحق المراد بيعه: ");
        int id = getValidInput();

        System.out.print("الكمية المراد بيعها: ");
        int quantity = getValidInput();

        store.sellAccessory(id, quantity);
    }

    /**
     * الخيار 4: عرض التقارير (الشخص الثالث)
     */
    private static void viewReports() {
        while (true) {
            System.out.println("\n════════════════════════════════════════════════════════════════════");
            System.out.println("                        التقارير");
            System.out.println("════════════════════════════════════════════════════════════════════");
            System.out.println("أ - تقرير جميع الملحقات");
            System.out.println("ب - تقرير الملحقات حسب النوع والسعر");
            System.out.println("ج - تقرير الملحقات برقم الطلب (Order Limit)");
            System.out.println("د - العودة للقائمة الرئيسية");
            System.out.println("════════════════════════════════════════════════════════════════════");
            System.out.print("اختر التقرير: ");
            String choice = input.next().toLowerCase();

            switch (choice) {
                case "أ":
                case "a":
                    store.printAllAccessories();
                    break;

                case "ب":
                case "b":
                    printByTypeAndPrice();
                    break;

                case "ج":
                case "c":
                    printByOrderLimit();
                    break;

                case "د":
                case "d":
                    return;

                default:
                    System.out.println("\n❌ خيار غير صحيح. يرجى المحاولة مجدداً.");
            }
        }
    }

    /**
     * تقرير الملحقات حسب النوع والسعر
     */
    private static void printByTypeAndPrice() {
        System.out.println("\nنوع الجهاز:");
        System.out.println("  1 - ماوس");
        System.out.println("  2 - لوحة مفاتيح");
        System.out.println("  3 - شاشة");
        System.out.println("  4 - طابعة");
        System.out.print("اختر النوع: ");
        int type = getValidInput();

        System.out.print("السعر الأقصى: ");
        double priceLimit = getValidDoubleInput();

        store.printAccessoriesByTypeAndPrice(type, priceLimit);
    }

    /**
     * تقرير الملحقات برقم الطلب
     */
    private static void printByOrderLimit() {
        System.out.print("أدخل حد الطلب (Order Limit): ");
        int orderLimit = getValidInput();

        store.printAccessoriesWithLowQuantity(orderLimit);
    }

    /**
     * عرض رسالة الخروج
     */
    private static void exitProgram() {
        System.out.println("\n╔═════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                     ║");
        System.out.println("║                  شكراً لاستخدام البرنامج                          ║");
        System.out.println("║                  Thank you for using our system                   ║");
        System.out.println("║                                                                     ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════╝\n");
    }

    /**
     * الحصول على مدخل صحيح من المستخدم (عدد صحيح)
     */
    private static int getValidInput() {
        while (true) {
            try {
                return Integer.parseInt(input.next());
            } catch (NumberFormatException e) {
                System.out.print("❌ إدخال غير صحيح. يرجى إدخال رقم صحيح: ");
            }
        }
    }

    /**
     * الحصول على مدخل صحيح من المستخدم (رقم عشري)
     */
    private static double getValidDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(input.next());
            } catch (NumberFormatException e) {
                System.out.print("❌ إدخال غير صحيح. يرجى إدخال رقم صحيح: ");
            }
        }
    }
}
