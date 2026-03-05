public class Calculator {

    public static int evaluate(String expression, int a, int b) {
        Stack stack = new Stack();
        String[] list = expression.split(" ");

        for (String el : list) {
            if (el.equals("+") || el.equals("-") || el.equals("*")) {
                StackNode operand2 = stack.remove();
                StackNode operand1 = stack.remove();

                if (operand1 == null || operand2 == null) {
                    throw new IllegalArgumentException("Недостаточно операндов для операции " + el);
                }

                int result;
                switch (el) {
                    case "+":
                        result = operand1.getValue() + operand2.getValue();
                        break;
                    case "-":
                        result = operand1.getValue() - operand2.getValue();
                        break;
                    case "*":
                        result = operand1.getValue() * operand2.getValue();
                        break;
                    default:
                        throw new IllegalArgumentException("Неизвестная операция: " + el);
                }


                stack.add(new StackNode(result));

            } else {
                int value;
                switch (el) {
                    case "A":
                        value = a;
                        break;
                    case "B":
                        value = b;
                        break;
                    default:
                        throw new IllegalArgumentException("Недопустимый операнд: " + el);
                }


                stack.add(new StackNode(value));
            }
        }


        if (stack.getSize() != 1) {
            throw new IllegalArgumentException("Некорректное выражение: в стеке осталось " +
                    stack.getSize() + " элементов");
        }

        return stack.remove().getValue();
    }

}
