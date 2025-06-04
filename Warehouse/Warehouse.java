public class Warehouse {
    private static int processedPackages = 0;
    
    public static double calculateShippingWeight(double packageWeight, boolean isHighPriority, String packageType, String deliverySpeed) throws IllegalArgumentException {
        if (packageWeight <= 0 || packageWeight > 1000) {
            throw new IllegalArgumentException("El peso del paquete debe estar entre 0.1 kg y 1000 kg.");
        }

        if (processedPackages >= 500) {
            System.out.println("Advertencia: Se alcanzó el límite máximo de paquetes procesados por día.");
        }

        double handlingFee = 2.0;
        if ("fragile".equalsIgnoreCase(packageType)) {
            handlingFee = 5.0;  // Aumenta la tarifa de manejo para artículos frágiles
        } else if ("bulky".equalsIgnoreCase(packageType)) {
            handlingFee = 7.0;  // Aumenta la tarifa de manejo para artículos voluminosos
        }

        double priorityFactor = isHighPriority ? 1.1 : 1.0;
        if ("fast".equalsIgnoreCase(deliverySpeed)) {
            priorityFactor = 1.2; // Aumenta el factor de prioridad para entregas rápidas
        }

        processedPackages++;  // Incrementa el contador de paquetes procesados
        
        return (packageWeight + handlingFee) * priorityFactor;
    }

    public static void main(String[] args) {
        try {
            System.out.println(calculateShippingWeight(9.0, true, "standard", "fast"));
            System.out.println(calculateShippingWeight(5.5, false, "fragile", "standard"));
            System.out.println(calculateShippingWeight(0.3, true, "bulky", "fast"));
            System.out.println(calculateShippingWeight(1.0, true, "unknown", "fast")); // Should throw error
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

