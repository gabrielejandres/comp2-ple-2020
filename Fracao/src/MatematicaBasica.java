public class MatematicaBasica {

    /**
     *
     * @param dividendo O número que será dividido (maior número)
     * @param divisor O número pelo qual o dividendo será dividido
     * @return maior divisor comum entre dividendo e divisor
     */
    public static int calculaMdc(int dividendo, int divisor) {
        if( divisor > dividendo ) {
            int aux = dividendo;
            dividendo = divisor;
            divisor = aux;
        }
        return dividendo % divisor == 0 ? divisor : calculaMdc(divisor, dividendo % divisor);
    }
}
