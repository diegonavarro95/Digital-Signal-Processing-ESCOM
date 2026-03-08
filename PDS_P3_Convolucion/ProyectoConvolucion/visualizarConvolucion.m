function visualizarConvolucion(str_f, str_g)
    % --- Configuración de Entrada ---
    fs = 100; 
    t = -30:1/fs:50; 
    
    f_func = str2func(['@(t) (', str_f, ') + 0.*t']);
    g_func = str2func(['@(t) (', str_g, ') + 0.*t']);

    vf = f_func(t);
    vg = g_func(t);

    % --- Cálculo Real de Convolución ---
    res_conv = conv(vf, vg) / fs;
    t_conv = linspace(t(1)+t(1), t(end)+t(end), length(res_conv));

    vis_idx = find(abs(res_conv) > 1e-3);
    if isempty(vis_idx), error('La convolución es cero en todo el rango.'); end
    t_lims = [t_conv(vis_idx(1))-2, t_conv(vis_idx(end))+2];

    % --- Configuración Visual ---
    fig = figure('Color', 'w', 'Units', 'normalized', 'Position', [0.1 0.1 0.8 0.8]);
    
    ax1 = subplot(2,1,1, 'Color', 'w', 'XColor', 'k', 'YColor', 'k'); hold on; grid on;
    h_f = plot(ax1, t, vf, 'b', 'LineWidth', 2.5, 'DisplayName', 'f(\tau)'); % Azul
    h_g = plot(ax1, t, zeros(size(t)), 'r', 'LineWidth', 2.5, 'DisplayName', 'g(t-\tau)'); % Rojo

    % Área de intersección naranja claro
    h_fill = fill(ax1, NaN, NaN, [1 0.6 0.2], 'FaceAlpha', 0.4, 'EdgeColor', 'none', 'DisplayName', 'Área de Intersección'); 
    title('Desplazamiento e Intersección Geométrica', 'Color', 'k', 'FontSize', 12);
    legend('TextColor', 'k', 'Location', 'best');

    ax2 = subplot(2,1,2, 'Color', 'w', 'XColor', 'k', 'YColor', 'k'); hold on; grid on;
    plot(ax2, t_conv, res_conv, 'Color', [0.7 0.7 0.7], 'LineWidth', 1, 'LineStyle', '--'); 
    h_anim = animatedline(ax2, 'Color', [0.9 0.4 0], 'LineWidth', 3); % Naranja oscuro
    title('Resultado: (f * g)(t)', 'Color', 'k', 'FontSize', 12);

    xlim(ax1, t_lims);
    ylim(ax1, [min([vf, vg, -1])*1.5, max([vf, vg, 1])*1.5]);
    xlim(ax2, t_lims);
    ylim(ax2, [min(res_conv)-1, max(res_conv)+1]);

    % --- Animación Controlada ---
    t_shift = t_lims(1):0.1:t_lims(2); 
    dt = 0.03; 

    for ts = t_shift
        g_shifted = g_func(ts - t);
        set(h_g, 'XData', t, 'YData', g_shifted);
        
        prod = vf .* g_shifted;
        idx_ov = prod ~= 0; 
        
        if any(idx_ov)
            t_ov = t(idx_ov);
            vf_ov = vf(idx_ov);
            vg_ov = g_shifted(idx_ov);
            
            y_lower_ov = zeros(size(t_ov));
            y_upper_ov = zeros(size(t_ov));
            
            mask_diff = (vf_ov .* vg_ov < 0);
            mask_same = ~mask_diff;
            mask_same_pos = mask_same & (vf_ov > 0);
            mask_same_neg = mask_same & (vf_ov < 0);
            
            y_lower_ov(mask_diff) = vf_ov(mask_diff); 
            y_upper_ov(mask_diff) = vg_ov(mask_diff);
            
            y_lower_ov(mask_same_pos) = 0; 
            y_upper_ov(mask_same_pos) = min(vf_ov(mask_same_pos), vg_ov(mask_same_pos));
            
            y_lower_ov(mask_same_neg) = max(vf_ov(mask_same_neg), vg_ov(mask_same_neg)); 
            y_upper_ov(mask_same_neg) = 0;
            
            set(h_fill, 'XData', [t_ov, fliplr(t_ov)], 'YData', [y_upper_ov, fliplr(y_lower_ov)]);
        else
            set(h_fill, 'XData', NaN, 'YData', NaN);
        end
        
        [~, idx_c] = min(abs(t_conv - ts));
        addpoints(h_anim, ts, res_conv(idx_c));
        
        drawnow; 
        pause(dt); 
    end
    waitfor(fig);
end