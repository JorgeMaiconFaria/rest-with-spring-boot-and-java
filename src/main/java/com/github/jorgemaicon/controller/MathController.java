package com.github.jorgemaicon.controller;

import com.github.jorgemaicon.exception.UnsupportedMathOperationException;
import com.github.jorgemaicon.math.SimpleMath;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.jorgemaicon.util.ConvertToDouble.convertToDouble;
import static com.github.jorgemaicon.util.VerifyNumeric.isNumeric;

@RestController
@RequestMapping("/math")
public class MathController {

    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws UnsupportedMathOperationException {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return SimpleMath.sum(convertToDouble(numberOne), convertToDouble(numberOne));
    }

    @RequestMapping("/subtract/{numberOne}/{numberTwo}")
    public Double subtract(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws UnsupportedMathOperationException {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return SimpleMath.subtract(convertToDouble(numberOne), convertToDouble(numberOne));
    }

    @RequestMapping("/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws UnsupportedMathOperationException {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return SimpleMath.multiplication(convertToDouble(numberOne), convertToDouble(numberOne));
    }

    @RequestMapping("/division/{numberOne}/{numberTwo}")
    public Double division(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws UnsupportedMathOperationException {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }
        return SimpleMath.division(convertToDouble(numberOne), convertToDouble(numberOne));
    }

    @RequestMapping("/squareroot/{numberOne}")
    public Double squareRoot(@PathVariable("numberOne") String numberOne) throws UnsupportedMathOperationException {
        if(!isNumeric(numberOne)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        Double numberOneConverted = convertToDouble(numberOne);

        return SimpleMath.squareRoot(numberOneConverted);
    }

    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws UnsupportedMathOperationException {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return SimpleMath.mean(convertToDouble(numberOne), convertToDouble(numberOne));
    }
}
