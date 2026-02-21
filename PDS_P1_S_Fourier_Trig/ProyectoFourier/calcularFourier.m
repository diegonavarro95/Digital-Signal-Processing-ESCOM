function [a0_tex, an_tex, bn_tex, w0_tex, serie_plano] = calcularFourier(func_str, lim_a_str, lim_b_str, N)
    syms t n;
    assume(n, 'integer');
    
    f = str2sym(func_str); 
    lim_a_sym = str2sym(lim_a_str); 
    lim_b_sym = str2sym(lim_b_str);
    
    T_sym = lim_b_sym - lim_a_sym;
    w0_sym = 2*sym(pi)/T_sym;

    a0 = simplify((2/T_sym) * int(f, t, lim_a_sym, lim_b_sym));
    an = simplify((2/T_sym) * int(f * cos(n*w0_sym*t), t, lim_a_sym, lim_b_sym));
    bn = simplify((2/T_sym) * int(f * sin(n*w0_sym*t), t, lim_a_sym, lim_b_sym));

    serie = a0/2 + symsum(an*cos(n*w0_sym*t) + bn*sin(n*w0_sym*t), n, 1, N);

    a0_tex = char(latex(a0));
    an_tex = char(latex(an));
    bn_tex = char(latex(bn));
    w0_tex = char(latex(w0_sym));

    serie_plano = char(serie);

    % Graficación
    lim_a_num = double(lim_a_sym);
    lim_b_num = double(lim_b_sym);

    figure(1); 
    clf; 
    fplot(f, [lim_a_num, lim_b_num], 'r--', 'LineWidth', 2); hold on;
    fplot(serie, [lim_a_num, lim_b_num], 'b'); grid on;
    title(['Aproximación N = ', num2str(N)]);
    legend('Original', 'Serie de Fourier');
end