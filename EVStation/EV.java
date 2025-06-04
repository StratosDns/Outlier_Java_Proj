public class EV {
    public static double calculateChargingCost(double kWhUsed, String userType, boolean isPeakHours, boolean isPremiumMember) {
        if (kWhUsed < 1 || kWhUsed > 100) {
            throw new IllegalArgumentException("Error: Los kWh consumidos deben estar entre 1 y 100.");
        }

        if (!userType.equalsIgnoreCase("standard") && !userType.equalsIgnoreCase("member")) {
            throw new IllegalArgumentException("Error: Tipo de usuario inválido. Debe ser 'estándar' o 'miembro'.");
        }

        double baseRate = userType.equalsIgnoreCase("member") ? 0.15 : 0.20;
        double timeFactor = isPeakHours ? 5.0 : 0.0;
        double membershipDiscount = isPremiumMember ? -3.0 : 0.0;

        return (kWhUsed * baseRate) + timeFactor + membershipDiscount;
    }

    public static void main(String[] args) {
        try {
            System.out.println(calculateChargingCost(50, "member", true, true)); 
            // Salida esperada: costo con descuento y cargo por hora pico
            
            System.out.println(calculateChargingCost(30, "standard", false, false)); 
            // Salida esperada: tarifa normal
            
            //System.out.println(calculateChargingCost(0, "member", true, false)); 
            // Debería generar un error
            
            //System.out.println(calculateChargingCost(120, "standard", false, false)); 
            // Debería generar un error
            
            System.out.println(calculateChargingCost(20, "VIP", true, true)); 
            // Debería generar un error
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
