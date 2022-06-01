package objects;

import io.fries.koans.Koan;

import static io.fries.koans.KoanAssert.__;
import static io.fries.koans.KoanAssert.assertThat;

@SuppressWarnings("all")
class EnumsKoans {

    enum Colors {
        RED, GREEN, BLUE
    }

    @Koan
    void basic_enums() {
        Colors blue = Colors.BLUE;

        assertThat(blue == Colors.BLUE).isEqualTo(true);
        assertThat(blue == Colors.RED).isEqualTo(false);
        assertThat(blue instanceof Colors).isEqualTo(true);
    }

    @Koan
    void basic_enum_access() {
        Colors[] colorArray = Colors.values();

        assertThat(colorArray[2]).isEqualTo(Colors.BLUE);
    }

    enum HexadecimalColors {
        RED(0xFF0000), GREEN(0x00FF00), BLUE(0x0000FF);

        private int hexadecimalValue;

        HexadecimalColors(final int hexadecimalValue) {
            this.hexadecimalValue = hexadecimalValue;
        }
    }

    @Koan
    void enums_with_attributes() {
        assertThat(HexadecimalColors.BLUE.hexadecimalValue > HexadecimalColors.RED.hexadecimalValue).isEqualTo(false);
    }

    enum MixableColors {
        RED(0xFF0000), GREEN(0x00FF00), BLUE(0x0000FF);

        private int hexadecimalValue;

        MixableColors(final int hexadecimalValue) {
            this.hexadecimalValue = hexadecimalValue;
        }

        int mix(MixableColors color) {
            return hexadecimalValue + color.hexadecimalValue;
        }
    }

    @Koan
    void enums_with_methods() {
        assertThat(MixableColors.RED.mix(MixableColors.BLUE)).isEqualTo(0xFF00FF);
    }
}
