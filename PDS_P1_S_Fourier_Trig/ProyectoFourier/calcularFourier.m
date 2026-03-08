function [a0_tex, an_tex, bn_tex, w0_tex, serie_plano] = calcularFourier(func_str, lim_a_str, lim_b_str, N)

    syms t n
    assume(n,'integer')

    f = str2sym(func_str);
    lim_a_sym = str2sym(lim_a_str);
    lim_b_sym = str2sym(lim_b_str);

    T_sym = lim_b_sym - lim_a_sym;
    w0_sym = 2*pi/T_sym;

    % coeficientes
    a0 = simplify((2/T_sym) * int(f, t, lim_a_sym, lim_b_sym));
    an = simplify((2/T_sym) * int(f*cos(n*w0_sym*t), t, lim_a_sym, lim_b_sym));
    bn = simplify((2/T_sym) * int(f*sin(n*w0_sym*t), t, lim_a_sym, lim_b_sym));

    % construir serie manualmente
    serie = a0/2;

    for k = 1:N
        serie = serie + subs(an,n,k)*cos(k*w0_sym*t) + subs(bn,n,k)*sin(k*w0_sym*t);
    end

    % TEX para Java
    a0_tex = char(latex(a0));
    an_tex = char(latex(an));
    bn_tex = char(latex(bn));
    w0_tex = char(latex(w0_sym));

    serie_plano = char(serie);

    % límites numéricos
    lim_a_num = double(lim_a_sym);
    lim_b_num = double(lim_b_sym);

    % gráfica
    figure(1)
    clf

    fplot(f,[lim_a_num lim_b_num],'r--','LineWidth',2)
    hold on

    fplot(serie,[lim_a_num lim_b_num],'b','LineWidth',1.5)

    grid on
    title(['Aproximación Fourier  N = ', num2str(N)])
    legend('Original','Serie de Fourier')

    hold off
    drawnow

end