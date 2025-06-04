public class Warehouse1 {
    private static int processedPackages = 0;
    private static final int MAX_PACKAGES_PER_DAY = 500;

    public static double calculateShippingWeight(double packageWeight, boolean isHighPriority, String packageType, String deliverySpeed) {
        if (packageWeight <= 0 || packageWeight > 1000) {
            throw new IllegalArgumentException("Error: El peso del paquete debe estar entre 0.1 kg y 1000 kg.");
        }

        if (!packageType.equalsIgnoreCase("standard") && 
            !packageType.equalsIgnoreCase("fragile") && 
            !packageType.equalsIgnoreCase("bulky")) {
            throw new IllegalArgumentException("Error: Tipo de paquete inválido. Debe ser 'standard', 'fragile' o 'bulky'.");
        }

        if (!deliverySpeed.equalsIgnoreCase("fast") && !deliverySpeed.equalsIgnoreCase("standard")) {
            throw new IllegalArgumentException("Error: Velocidad de entrega inválida. Debe ser 'fast' o 'standard'.");
        }

        if (processedPackages >= MAX_PACKAGES_PER_DAY) {
            System.out.println("Warning: Se ha alcanzado el límite diario de paquetes procesados.");
        }

        double handlingFee;
        switch (packageType.toLowerCase()) {
            case "fragile":
                handlingFee = 5.0;
                break;
            case "bulky":
                handlingFee = 7.0;
                break;
            default:
                handlingFee = 2.0;
        }

        double priorityFactor = isHighPriority ? 1.1 : 1.0;
        if (deliverySpeed.equalsIgnoreCase("fast")) {
            priorityFactor = 1.2;
        }

        processedPackages++;
        return (packageWeight + handlingFee) * priorityFactor;
    }

    public static void main(String[] args) {
        try {
            System.out.println(calculateShippingWeight(10.0, true, "standard", "fast"));
            System.out.println(calculateShippingWeight(5.5, false, "fragile", "standard"));
            System.out.println(calculateShippingWeight(0.0, true, "bulky", "fast")); // Should throw error
            //System.out.println(calculateShippingWeight(2000.0, false, "fragile", "standard")); // Should throw error
            System.out.println(calculateShippingWeight(1.0, true, "unknown", "fast")); // Should throw error
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
